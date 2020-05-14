/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.debugger.test;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.test.TestRoot;
import org.junit.runner.RunWith;

/*
 * This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}.
 * DO NOT MODIFY MANUALLY.
 */
@TestRoot("jvm-debugger/test")
@SuppressWarnings("all")
@TestMetadata("testData/fileRanking")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class FileRankingTestGenerated extends AbstractFileRankingTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("anonymousClasses.kt")
    public void testAnonymousClasses() throws Exception {
        runTest("testData/fileRanking/anonymousClasses.kt");
    }

    @TestMetadata("differentFlags.kt")
    public void testDifferentFlags() throws Exception {
        runTest("testData/fileRanking/differentFlags.kt");
    }

    @TestMetadata("init.kt")
    public void testInit() throws Exception {
        runTest("testData/fileRanking/init.kt");
    }

    @TestMetadata("lambdas.kt")
    public void testLambdas() throws Exception {
        runTest("testData/fileRanking/lambdas.kt");
    }

    @TestMetadata("multilinePrimaryConstructor.kt")
    public void testMultilinePrimaryConstructor() throws Exception {
        runTest("testData/fileRanking/multilinePrimaryConstructor.kt");
    }

    @TestMetadata("multilinePrimaryConstructorWithBody.kt")
    public void testMultilinePrimaryConstructorWithBody() throws Exception {
        runTest("testData/fileRanking/multilinePrimaryConstructorWithBody.kt");
    }

    @TestMetadata("parametersWithUnloadedClass.kt")
    public void testParametersWithUnloadedClass() throws Exception {
        runTest("testData/fileRanking/parametersWithUnloadedClass.kt");
    }

    @TestMetadata("propertyDelegates.kt")
    public void testPropertyDelegates() throws Exception {
        runTest("testData/fileRanking/propertyDelegates.kt");
    }

    @TestMetadata("sameClassName.kt")
    public void testSameClassName() throws Exception {
        runTest("testData/fileRanking/sameClassName.kt");
    }

    @TestMetadata("sameClassNameDifferentMethodNames.kt")
    public void testSameClassNameDifferentMethodNames() throws Exception {
        runTest("testData/fileRanking/sameClassNameDifferentMethodNames.kt");
    }

    @TestMetadata("simple.kt")
    public void testSimple() throws Exception {
        runTest("testData/fileRanking/simple.kt");
    }

    @TestMetadata("topLevel.kt")
    public void testTopLevel() throws Exception {
        runTest("testData/fileRanking/topLevel.kt");
    }
}
