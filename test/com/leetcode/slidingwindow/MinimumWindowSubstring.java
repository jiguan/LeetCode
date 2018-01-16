package com.leetcode.slidingwindow;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] times = new int[128];
        for (char c : t.toCharArray()) {
            ++times[c];
        }

        int begin = 0, end = 0, diff = t.length();
        int i = 0, minLength = Integer.MAX_VALUE;
        while (end < s.length()) {
            char c = s.charAt(end++);
            if (--times[c] >= 0) --diff;

            while (diff == 0) {
                if (end - begin < minLength) {
                    minLength = end - begin;
                    i = begin;
                }
                char kickout = s.charAt(begin++);
                if (++times[kickout] == 1) {
                    ++diff;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(i, i + minLength);
    }

    @Test
    public void test0() {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        assertEquals("BANC", minWindow(S, T));
    }
}
