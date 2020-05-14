/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.completion.test;

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
@TestRoot("completion")
@SuppressWarnings("all")
@TestMetadata("testData/basic/java8")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class Java8BasicCompletionTestGenerated extends AbstractJava8BasicCompletionTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("CollectionMethods.kt")
    public void testCollectionMethods() throws Exception {
        runTest("testData/basic/java8/CollectionMethods.kt");
    }

    @TestMetadata("StreamMethods.kt")
    public void testStreamMethods() throws Exception {
        runTest("testData/basic/java8/StreamMethods.kt");
    }
}
