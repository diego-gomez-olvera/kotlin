/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.spring.tests.references;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("ultimate/testData/spring/core/references/completion/handler")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class SpringReferenceCompletionHandlerTestGenerated extends AbstractSpringReferenceCompletionHandlerTest {
    public void testAllFilesPresentInHandler() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("ultimate/testData/spring/core/references/completion/handler"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
    }

    @TestMetadata("packageReferenceEnter.kt")
    public void testPackageReferenceEnter() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("ultimate/testData/spring/core/references/completion/handler/packageReferenceEnter.kt");
        doTest(fileName);
    }

    @TestMetadata("packageReferenceTab.kt")
    public void testPackageReferenceTab() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("ultimate/testData/spring/core/references/completion/handler/packageReferenceTab.kt");
        doTest(fileName);
    }

    @TestMetadata("qualifierReferenceEnter.kt")
    public void testQualifierReferenceEnter() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("ultimate/testData/spring/core/references/completion/handler/qualifierReferenceEnter.kt");
        doTest(fileName);
    }

    @TestMetadata("qualifierReferenceTab.kt")
    public void testQualifierReferenceTab() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("ultimate/testData/spring/core/references/completion/handler/qualifierReferenceTab.kt");
        doTest(fileName);
    }

    @TestMetadata("scopeReference.kt")
    public void testScopeReference() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("ultimate/testData/spring/core/references/completion/handler/scopeReference.kt");
        doTest(fileName);
    }

    @TestMetadata("springBeanReferenceEnter.kt")
    public void testSpringBeanReferenceEnter() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("ultimate/testData/spring/core/references/completion/handler/springBeanReferenceEnter.kt");
        doTest(fileName);
    }

    @TestMetadata("springBeanReferenceTab.kt")
    public void testSpringBeanReferenceTab() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("ultimate/testData/spring/core/references/completion/handler/springBeanReferenceTab.kt");
        doTest(fileName);
    }
}
