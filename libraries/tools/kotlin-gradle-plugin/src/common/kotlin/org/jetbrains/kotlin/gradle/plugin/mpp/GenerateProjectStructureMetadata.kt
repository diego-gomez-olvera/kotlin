/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.plugin.mpp

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.gradle.utils.notCompatibleWithConfigurationCache
import java.io.File

open class GenerateProjectStructureMetadata : DefaultTask() {

    init {
        notCompatibleWithConfigurationCache("Task $name does not support Gradle Configuration Cache. Check KT-49933 for more info")
    }

    @get:Internal
    internal lateinit var lazyKotlinProjectStructureMetadata: Lazy<KotlinProjectStructureMetadata>

    @get:Nested
    internal val kotlinProjectStructureMetadata: KotlinProjectStructureMetadata
        get() = lazyKotlinProjectStructureMetadata.value

    @get:OutputFile
    val resultFile: File
        get() = project.buildDir.resolve("kotlinProjectStructureMetadata/$MULTIPLATFORM_PROJECT_METADATA_JSON_FILE_NAME")

    @TaskAction
    fun generateMetadataXml() {
        resultFile.parentFile.mkdirs()
        val resultString = kotlinProjectStructureMetadata.toJson()
        resultFile.writeText(resultString)
    }
}

internal const val MULTIPLATFORM_PROJECT_METADATA_FILE_NAME = "kotlin-project-structure-metadata.xml"
internal const val MULTIPLATFORM_PROJECT_METADATA_JSON_FILE_NAME = "kotlin-project-structure-metadata.json"