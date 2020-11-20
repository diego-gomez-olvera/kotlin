/*
 * Copyright 2020 The JSpecify Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jspecify.annotations.DefaultNonNull;
import org.jspecify.annotations.Nullable;
import org.jspecify.annotations.NullnessUnspecified;

@DefaultNonNull
class AnnotatedTypeParameterUnspec {
  // jspecify_unrecognized_location
  interface Lib1<@NullnessUnspecified T> {}

  // jspecify_unrecognized_location
  interface Lib2<@NullnessUnspecified T extends Object> {}

  // jspecify_unrecognized_location
  interface Lib3<@NullnessUnspecified T extends @Nullable Object> {}
}

static class Checker {
  void main(
          // jspecify_nullness_mismatch
          AnnotatedTypeParameterUnspec.Lib1<@Nullable String> x1,
          // jspecify_nullness_mismatch
          AnnotatedTypeParameterUnspec.Lib2<@Nullable String> x2,
          AnnotatedTypeParameterUnspec.Lib3<@Nullable String> x3
  ) {

  }
}
