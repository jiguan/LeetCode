package com.leetcode.divide;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Find the length of the longest substring T of a given string (consists of
 * lowercase letters only) such that every character in T appears no less than k
 * times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        if (n == 0 || n < k) return 0;

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        char badChar = 0;
        for (int i = 0; i < 26; ++i) {
            if (count[i] > 0 && count[i] < k) {
                badChar = (char) (i + 'a');
                break;
            }
        }
        if (badChar == 0) return n;
        String[] subs = s.split(badChar + "");
        int res = 0;
        for (String sub : subs) {
            res = Math.max(res, longestSubstring(sub, k));
        }
        return res;
    }

    public int longestSubstring1(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        return helper(s.toCharArray(), 0, s.length(), k);
    }

    private int helper(char[] arr, int start, int end, int k) {
        if (start >= end || k > end - start) return 0;
        int[] count = new int[26];
        for (int i = start; i < end; ++i) {
            ++count[arr[i] - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (count[i] == 0) continue;
            if (count[i] < k) {
                for (int j = start; j < end; ++j) {
                    if (arr[j] == i + 'a') {
                        return Math.max(helper(arr, start, j, k), helper(arr, j + 1, end, k));
                    }
                }
            }
        }
        return end - start;
    }

    @Test
    public void test0() {
        String s = "aaabb";
        int k = 3;
        assertEquals(3, longestSubstring(s, k));
    }

    @Test
    public void test1() {
        String s = "ababbc";
        int k = 2;
        assertEquals(5, longestSubstring(s, k));
    }

    @Test
    public void test2() {
        String s = "bbaaacbd";
        int k = 3;
        assertEquals(3, longestSubstring(s, k));
    }
}
