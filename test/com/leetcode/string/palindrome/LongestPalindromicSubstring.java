package com.leetcode.string.palindrome;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        int low = 0, high = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            int tmp = i;
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && ((j - i < 3) || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    tmp = j;
                }
            }
            if (tmp - i + 1 > high - low) {
                low = i;
                high = tmp + 1;
            }
        }
        return s.substring(low, high);
    }

    @Test
    public void test0() {
        String s = "cabad";
        String expected = "aba";
        assertEquals(expected, longestPalindrome(s));
    }
    
    @Test
    public void test1() {
        String s = "aaaa";
        String expected = "aaaa";
        assertEquals(expected, longestPalindrome(s));
    }
}
