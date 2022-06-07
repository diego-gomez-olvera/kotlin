/*
 * Copyright 2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "KString.h"

#include "gtest/gtest.h"
#include "gmock/gmock.h"

void checkContentsEquality(ArrayHeader* arrayHeader, const char16_t* expected) {
    EXPECT_THAT(arrayHeader->count_, std::char_traits<char16_t>::length(expected));
    const size_t charArrayHeaderSize = 16;  // alignUp(sizeof(ArrayHeader), alignof(char16_t))
    const char16_t* actual = (char16_t*)((char*)arrayHeader + charArrayHeaderSize);
    for (size_t i=0; i<arrayHeader->count_; i++) {
        EXPECT_THAT(actual[i], expected[i]);
    }
    EXPECT_THAT(arrayHeader->obj()->permanent(), true);
}

TEST(KStringTest, mallocString_ascii) {
    const char* ascii = "foo";
    EXPECT_THAT(strlen(ascii), 3);
    const char16_t* expected = u"foo";
    EXPECT_THAT(std::char_traits<char16_t>::length(expected), 3);

    auto result = mallocString(ascii)->array();
    checkContentsEquality(result, expected);
}

TEST(KStringTest, mallocString_misc) {
    const char* non_ascii = "-£öü²ソニーΣℜ∫♣‰€";
    EXPECT_THAT(strlen(non_ascii), 35);
    const char16_t* expected = u"-£öü²ソニーΣℜ∫♣‰€";
    EXPECT_THAT(std::char_traits<char16_t>::length(expected), 14);

    auto result = mallocString(non_ascii)->array();
    checkContentsEquality(result, expected);
}

TEST(KStringTest, mallocString_empty) {
    const char* empty = "";
    EXPECT_THAT(strlen(empty), 0);
    const char16_t* expected = u"";
    EXPECT_THAT(std::char_traits<char16_t>::length(expected), 0);

    auto result = mallocString(empty)->array();
    checkContentsEquality(result, expected);
}
