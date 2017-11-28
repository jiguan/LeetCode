package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        // dp stores the max length in the substring (i, j)
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; --i) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); ++j) {
                // compare the starting i and newly added j
                if(s.charAt(i) == s.charAt(j)) {
                    // dp[i+1][j-1] is the length in the substring (i+1, ... j-1)
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
    
    @Test
    public void test0() {
        String s = "bbbab";
        // The longest palindromic sequence is bbbb.
        assertEquals(4, longestPalindromeSubseq(s));
    }
}
