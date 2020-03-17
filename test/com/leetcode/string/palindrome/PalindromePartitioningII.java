package com.leetcode.string.palindrome;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PalindromePartitioningII {
    public int minCut(String s) {
        int len = s.length();
        // min cuts needed for a palindrome partitioning of 0 to j
        int[] dp = new int[len];
        for (int i = 0; i < len; ++i) {
            dp[i] = i;
        }

        for (int mid = 1; mid < len; ++mid) {
            // case even len
            for (int i = mid - 1, j = mid; i >= 0 && j < len && s.charAt(i) == s.charAt(j); i--, j++) {
                int cutNum = (i == 0) ? 0 : dp[i - 1] + 1;
                dp[j] = Math.min(dp[j], cutNum);
            }
            // case odd len
            for (int i = mid, j = mid; i >= 0 && j < len && s.charAt(i) == s.charAt(j); i--, j++) {
                int cutNum = (i == 0) ? 0 : dp[i - 1] + 1;
                dp[j] = Math.min(dp[j], cutNum);
            }
        }

        return dp[len - 1];
    }

    @Test
    public void test0() {
        String s = "aab";
        assertEquals(1, minCut(s));
    }
}
