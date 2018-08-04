package com.leetcode.dp.lookback;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String s = "abc";
        assertEquals(3, countSubstrings(s));
    }

    @Test
    public void test1() {
        String s = "aaa";
        assertEquals(6, countSubstrings(s));
    }
}
