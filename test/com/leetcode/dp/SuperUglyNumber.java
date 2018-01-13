package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        int[] index = new int[primes.length];
        dp[0] = 1;
        for (int i = 1; i < dp.length; ++i) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; ++j) {
                dp[i] = Integer.min(dp[i], dp[index[j]] * primes[j]);
            }

            for (int j = 0; j < primes.length; ++j) {
                if (dp[i] == dp[index[j]] * primes[j]) {
                    index[j]++;
                }
            }
        }
        return dp[n - 1];
    }

    @Test
    public void test0() {
        int[] primes = new int[]{2, 7, 13, 19};
        // [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32]
        assertEquals(32, nthSuperUglyNumber(12, primes));
    }
}
