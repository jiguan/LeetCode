package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CoinChange {
    private Map<Integer, Integer> map = new HashMap<>();

    // https://leetcode.com/discuss/83289/understand-recursive-solution-using-java-with-explanations
    public int coinChange0(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (map.containsKey(amount)) return map.get(amount);
        int res = amount + 1;
        for (int coin : coins) {
            if (coin <= amount) {
                int num = coinChange(coins, amount - coin);
                if (num >= 0) {
                    res = Math.min(res, num + 1);
                }
            }
        }

        if (res == amount + 1) {
            res = -1;
        }
        map.put(amount, res);
        return res;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length && i >= coins[j]; j++) {
                int coin = coins[j];
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if(dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }

    public int coinChange1(int[] coins, int amount) {
        int[] arr = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            arr[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && arr[i - coins[j]] != Integer.MAX_VALUE) {
                    arr[i] = Integer.min(arr[i], arr[i - coins[j]] + 1);
                }
            }
        }
        int result = arr[amount] == Integer.MAX_VALUE ? -1 : arr[amount];
        return result;
    }
    @Test
    public void test0() {
        int[] coins = new int[]{2, 5, 1};
        int amount = 11;
        assertEquals(3, coinChange0(coins, amount));
    }

    @Test
    public void test1() {
        int[] coins = new int[]{2};
        int amount = 3;
        assertEquals(-1, coinChange0(coins, amount));
    }

    @Test
    public void test2() {
        int[] coins = new int[]{3, 5, 7};
        int amount = 18;
        assertEquals(4, coinChange0(coins, amount));
    }

    @Test
    public void test3() {
        int[] coins = new int[]{186, 419, 83, 408};
        int amount = 6249;
        assertEquals(20, coinChange0(coins, amount));
    }
}
