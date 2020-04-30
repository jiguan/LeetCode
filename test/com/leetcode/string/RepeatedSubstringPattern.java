package com.leetcode.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RepeatedSubstringPattern {
    // KMP check out the /LeetCode/test/com/algorithm/KnuthMorrisPratt.java
    public boolean repeatedSubstringPatternKmp(String s) {
        int len = s.length();
        // longest suffix-prefix
        int[] lsp = new int[len];

        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            // previous character's start position
            int j = lsp[i - 1];
            while (j != 0 && c != s.charAt(j)) {
                j = lsp[j - 1];
            }

            if (c == s.charAt(j)) {
                lsp[i] = j + 1;
            }
        }
        // If there is repeated substring pattern, the lsp would be like [0, 0, 0, 1, 2, 3, 4, 5, 6]
        // len - lsp[last] is the pattern length
        return lsp[len - 1] != 0 && len % (len - lsp[len - 1]) == 0;
    }


    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        // i - how many elements for each part
        for (int size = len / 2; size >= 1; size--) {
            if (len % size == 0) {
                // how many parts divide into
                int parts = len / size;
                String sub = s.substring(0, size);
                // [0, i) vs [i, 2i), [2i,   3i)
                for (int i = 1; i <= parts; i++) {
                    if (i == parts) return true;
                    if (!sub.equals(s.substring(i * size, (i + 1) * size))) break;
                }
            }
        }
        return false;
    }

    @Test
    public void test0() {
        String s = "abaabaa";
        assertFalse(repeatedSubstringPattern(s));
    }

    @Test
    public void test1() {
        String s = "abcabcabcabc";
        assertTrue(repeatedSubstringPattern(s));
    }
}
