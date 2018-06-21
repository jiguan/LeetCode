package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegerBreak {
    public int integerBreak(int n) {
        // dp[i] stores the max product for i
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int max = 1;
            for (int j = 1; 2 * j <= i; j++) {
                // break it into two parts: j and i - j
                int factor1 = Math.max(j, dp[j]);
                int factor2 = Math.max(i - j, dp[i - j]);
                max = Math.max(max, factor1 * factor2);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    @Test
    public void test0() {
        int n = 10;
        assertEquals(36, integerBreak(n));
    }
}
