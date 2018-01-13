package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int t2 = 0, t3 = 0, t5 = 0;
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.min(Math.min(dp[t2] * 2, dp[t3] * 3), dp[t5] * 5);
            if (dp[t2] * 2 == dp[i]) {
                ++t2;
            }
            if (dp[t3] * 3 == dp[i]) {
                ++t3;
            }
            if (dp[t5] * 5 == dp[i]) {
                ++t5;
            }
        }
        return dp[n - 1];
    }

    @Test
    public void test0() {
        assertEquals(12, nthUglyNumber(10));
    }
}
