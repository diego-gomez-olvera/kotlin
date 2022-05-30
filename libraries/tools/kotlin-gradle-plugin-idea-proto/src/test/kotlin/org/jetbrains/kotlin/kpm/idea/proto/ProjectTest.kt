/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kpm.idea.proto

import org.jetbrains.kotlin.gradle.kpm.idea.IdeaKpmProject
import org.jetbrains.kotlin.gradle.kpm.idea.testFixtures.TestInstances
import kotlin.test.Test

class ProjectTest : AbstractSerializationTest<IdeaKpmProject>() {

    override fun serialize(value: IdeaKpmProject): ByteArray {
        return value.toByteArray(this)
    }

    override fun deserialize(data: ByteArray): IdeaKpmProject {
        return IdeaKpmProject(data)
    }

    @Test
    fun `serialize - deserialize - sample 0`() = testSerialization(TestInstances.simpleProject)
}
