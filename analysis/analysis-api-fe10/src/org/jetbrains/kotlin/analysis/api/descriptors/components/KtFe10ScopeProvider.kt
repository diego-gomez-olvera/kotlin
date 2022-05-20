/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.components

import com.intellij.openapi.diagnostic.Logger
import org.jetbrains.kotlin.analysis.api.components.KtImplicitReceiver
import org.jetbrains.kotlin.analysis.api.components.KtScopeContext
import org.jetbrains.kotlin.analysis.api.components.KtScopeProvider
import org.jetbrains.kotlin.analysis.api.descriptors.KtFe10AnalysisSession
import org.jetbrains.kotlin.analysis.api.descriptors.components.base.Fe10KtAnalysisSessionComponent
import org.jetbrains.kotlin.analysis.api.descriptors.scopes.*
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.KtFe10FileSymbol
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.KtFe10PackageSymbol
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.base.KtFe10Symbol
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.KtFe10DescSymbol
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.toKtSymbol
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.toKtType
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased.base.KtFe10PsiSymbol
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased.base.getResolutionScope
import org.jetbrains.kotlin.analysis.api.descriptors.types.base.KtFe10Type
import org.jetbrains.kotlin.analysis.api.impl.base.scopes.KtEmptyScope
import org.jetbrains.kotlin.analysis.api.impl.base.scopes.KtCompositeScope
import org.jetbrains.kotlin.analysis.api.scopes.*
import org.jetbrains.kotlin.analysis.api.symbols.KtFileSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KtPackageSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KtSymbol
import org.jetbrains.kotlin.analysis.api.symbols.markers.KtSymbolWithMembers
import org.jetbrains.kotlin.analysis.api.lifetime.KtLifetimeToken
import org.jetbrains.kotlin.analysis.api.types.KtType
import org.jetbrains.kotlin.analysis.api.lifetime.withValidityAssertion
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.incremental.components.NoLookupLocation
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.scopes.ChainedMemberScope
import org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.resolve.scopes.MemberScope
import org.jetbrains.kotlin.resolve.scopes.utils.getImplicitReceiversHierarchy
import org.jetbrains.kotlin.util.containingNonLocalDeclaration
import org.jetbrains.kotlin.utils.Printer

internal class KtFe10ScopeProvider(
    override val analysisSession: KtFe10AnalysisSession
) : KtScopeProvider(), Fe10KtAnalysisSessionComponent {
    private companion object {
        val LOG = Logger.getInstance(KtFe10ScopeProvider::class.java)
    }

    override val token: KtLifetimeToken
        get() = analysisSession.token

    override fun getMemberScope(classSymbol: KtSymbolWithMembers): KtScope = withValidityAssertion {
        val descriptor = getDescriptor<ClassDescriptor>(classSymbol)
            ?: return getEmptyScope()

        return KtFe10ScopeMember(descriptor.unsubstitutedMemberScope, analysisContext)
    }

    override fun getDeclaredMemberScope(classSymbol: KtSymbolWithMembers): KtScope = withValidityAssertion {
        val descriptor = getDescriptor<ClassDescriptor>(classSymbol)
            ?: return getEmptyScope()

        return KtFe10ScopeMember(DeclaredMemberScope(descriptor), analysisContext)
    }

    private class DeclaredMemberScope(val allMemberScope: MemberScope, val owner: ClassDescriptor) : MemberScope {
        constructor(owner: ClassDescriptor) : this(owner.unsubstitutedMemberScope, owner)

        override fun getContributedVariables(name: Name, location: LookupLocation): Collection<PropertyDescriptor> {
            return allMemberScope.getContributedVariables(name, location).filter { it.containingDeclaration == owner }
        }

        override fun getContributedFunctions(name: Name, location: LookupLocation): Collection<SimpleFunctionDescriptor> {
            return allMemberScope.getContributedFunctions(name, location).filter { it.containingDeclaration == owner }
        }

        override fun getFunctionNames(): Set<Name> {
            return allMemberScope.getFunctionNames().filterTo(mutableSetOf()) { name ->
                getContributedFunctions(name, NoLookupLocation.FROM_IDE).isNotEmpty()
            }
        }

        override fun getVariableNames(): Set<Name> {
            return allMemberScope.getVariableNames().filterTo(mutableSetOf()) { name ->
                getContributedVariables(name, NoLookupLocation.FROM_IDE).isNotEmpty()
            }
        }

        override fun getClassifierNames(): Set<Name>? {
            return allMemberScope.getClassifierNames()?.filterTo(mutableSetOf()) { name ->
                getContributedClassifier(name, NoLookupLocation.FROM_IDE) != null
            }
        }

        override fun printScopeStructure(p: Printer) {
            allMemberScope.printScopeStructure(p)
        }

        override fun getContributedClassifier(name: Name, location: LookupLocation): ClassifierDescriptor? {
            return allMemberScope.getContributedClassifier(name, location)?.takeIf { it.containingDeclaration == owner }
        }

        override fun getContributedDescriptors(
            kindFilter: DescriptorKindFilter,
            nameFilter: (Name) -> Boolean
        ): Collection<DeclarationDescriptor> {
            return allMemberScope.getContributedDescriptors(kindFilter, nameFilter).filter { it.containingDeclaration == owner }
        }

    }

    override fun getDelegatedMemberScope(classSymbol: KtSymbolWithMembers): KtScope = withValidityAssertion {
        val descriptor = getDescriptor<ClassDescriptor>(classSymbol)
            ?: return getEmptyScope()

        // TODO: need to return delegated members only
        return KtFe10ScopeMember(descriptor.unsubstitutedMemberScope, analysisContext)
    }

    override fun getStaticMemberScope(symbol: KtSymbolWithMembers): KtScope = withValidityAssertion {
        val descriptor = getDescriptor<ClassDescriptor>(symbol) ?: return getEmptyScope()
        return KtFe10ScopeMember(descriptor.staticScope, analysisContext)
    }

    override fun getEmptyScope(): KtScope = withValidityAssertion {
        KtEmptyScope(token)
    }

    override fun getFileScope(fileSymbol: KtFileSymbol): KtScope = withValidityAssertion {
        require(fileSymbol is KtFe10FileSymbol)
        return KtFe10FileScope(fileSymbol.psi, analysisContext, token)
    }

    override fun getPackageScope(packageSymbol: KtPackageSymbol): KtScope = withValidityAssertion {
        require(packageSymbol is KtFe10PackageSymbol)
        val packageFragments = analysisContext.resolveSession.packageFragmentProvider.packageFragments(packageSymbol.fqName)
        val scopeDescription = "Compound scope for package \"${packageSymbol.fqName}\""
        val chainedScope = ChainedMemberScope.create(scopeDescription, packageFragments.map { it.getMemberScope() })
        return KtFe10PackageScope(chainedScope, packageSymbol, analysisContext)
    }

    override fun getCompositeScope(subScopes: List<KtScope>): KtScope = withValidityAssertion {
        return KtCompositeScope(subScopes, token)
    }

    override fun getTypeScope(type: KtType): KtScope = withValidityAssertion {
        require(type is KtFe10Type)
        return KtFe10ScopeMember(type.type.memberScope, analysisContext)
    }

    override fun getScopeContextForPosition(originalFile: KtFile, positionInFakeFile: KtElement): KtScopeContext = withValidityAssertion {
        val elementToAnalyze = positionInFakeFile.containingNonLocalDeclaration() ?: originalFile
        val bindingContext = analysisContext.analyze(elementToAnalyze)

        val lexicalScope = positionInFakeFile.getResolutionScope(bindingContext)
        if (lexicalScope != null) {
            val compositeScope = KtCompositeScope(listOf(KtFe10ScopeLexical(lexicalScope, analysisContext)), token)
            return KtScopeContext(compositeScope, collectImplicitReceivers(lexicalScope))
        }

        val fileScope = analysisContext.resolveSession.fileScopeProvider.getFileResolutionScope(originalFile)
        val compositeScope = KtCompositeScope(listOf(KtFe10ScopeLexical(fileScope, analysisContext)), token)
        return KtScopeContext(compositeScope, collectImplicitReceivers(fileScope))
    }

    private inline fun <reified T : DeclarationDescriptor> getDescriptor(symbol: KtSymbol): T? {
        return when (symbol) {
            is KtFe10DescSymbol<*> -> symbol.descriptor as? T
            is KtFe10PsiSymbol<*, *> -> symbol.descriptor as? T
            else -> {
                require(symbol is KtFe10Symbol) { "Unrecognized symbol implementation found" }
                null
            }
        }
    }

    private fun collectImplicitReceivers(scope: LexicalScope): MutableList<KtImplicitReceiver> {
        val result = mutableListOf<KtImplicitReceiver>()

        for (implicitReceiver in scope.getImplicitReceiversHierarchy()) {
            val type = implicitReceiver.type.toKtType(analysisContext)
            val ownerDescriptor = implicitReceiver.containingDeclaration
            val owner = ownerDescriptor.toKtSymbol(analysisContext)

            if (owner == null) {
                LOG.error("Unexpected implicit receiver owner: $ownerDescriptor (${ownerDescriptor.javaClass})")
                continue
            }

            result += KtImplicitReceiver(token, type, owner)
        }

        return result
    }
}
