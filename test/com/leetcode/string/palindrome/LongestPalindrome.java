package com.leetcode.string.palindrome;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Given a string which consists of lowercase or uppercase letters, find the
 * length of the longest palindromes that can be built with those letters.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int pair = 0;
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                pair++;
            } else {
                set.add(c);
            }
        }

        if (set.isEmpty()) {
            return 2 * pair;
        } else {
            return 2 * pair + 1;
        }
    }

    @Test
    public void test0() {
        String s = "abccccdd";
        assertEquals(7, longestPalindrome(s));
    }
}
