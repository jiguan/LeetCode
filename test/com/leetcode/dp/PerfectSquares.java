package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; ++i) {
            dp[i] = i;
            int s = 1;
            while (i - s * s >= 0) {
                dp[i] = Math.min(dp[i], dp[i - s * s] + 1);
                ++s;
            }
        }
        return dp[dp.length - 1];
    }

    @Test
    public void test0() {
        // 4 + 4 + 4
        assertEquals(3, numSquares(12));
    }
}
