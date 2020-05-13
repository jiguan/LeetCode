package com.leetcode.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.junit.Test;

// In one conversion you can convert all occurrences of one character in str1 to any other lowercase
// English character.
// => we could convert all letters in str1 into same character
// => key doestn't matter, values requires at least one unused character
//
// To realise the transformation:
// 1. transformation of link link ,like a -> b -> c:
// we do the transformation from end to begin, that is b->c then a->b
// 2. transformation of cycle, like a -> b -> c -> a:
// in this case we need a tmp
// c->tmp, b->c a->b and tmp->a
// Same as the process of swap two variable.
// In both case, there should at least one character that is unused, to use it as the tmp for
// transformation.
//
public class StringTransformsIntoAnotherString {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); ++i) {
            char ch1 = str1.charAt(i), ch2 = str2.charAt(i);
            if (map.getOrDefault(ch1, ch2) != ch2) return false;
            map.put(ch1, ch2);
        }

        return new HashSet<>(map.values()).size() < 26;
    }

    @Test
    public void test0() {
        String str1 = "aabcc", str2 = "ccdee";
        assertTrue(canConvert(str1, str2));
    }

    @Test
    public void test1() {
        String str1 = "abcdefghijklmnopqrstuvwxyz", str2 = "bcdefghijklmnopqrstuvwxyza";
        // after one convention, we will have 2 'a'
        // next time both are converted
        assertFalse(canConvert(str1, str2));
    }
}
