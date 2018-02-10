package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 0; i < coins.length; ++i) {
            for (int j = coins[i]; j <= amount; ++j) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    @Test
    public void test0() {
        int[] coins = new int[] { 1, 2, 5 };
        int amount = 5;
        assertEquals(4, change(amount, coins));  
    }

    @Test
    public void test1() {
        int[] coins = new int[] {};
        int amount = 0;
        assertEquals(1, change(amount, coins));
    }
}
