package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CoinChange {
    private Map<Integer, Integer> map = new HashMap<>();

    // https://leetcode.com/discuss/83289/understand-recursive-solution-using-java-with-explanations
    public int coinChange0(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (map.containsKey(amount)) return map.get(amount);
        int total = amount + 1;
        for (int coin : coins) {
            int num = 0;
            if (amount >= coin) {
                int coinNum = coinChange0(coins, amount - coin);
                if (coinNum >= 0) {
                    num = 1 + coinNum;
                }
            }
            if (num > 0) {
                total = Math.min(total, num);
            }
        }
        int result = total != amount + 1 ? total : -1;
        map.put(amount, result);
        return result;
    }

    public int coinChange(int[] coins, int amount) {
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
        int[] coins = new int[] { 2, 5, 1 };
        int amount = 11;
        assertEquals(3, coinChange(coins, amount));
    }

    @Test
    public void test1() {
        int[] coins = new int[] { 2 };
        int amount = 3;
        assertEquals(-1, coinChange(coins, amount));
    }

    @Test
    public void test2() {
        int[] coins = new int[] { 3, 5, 7 };
        int amount = 18;
        assertEquals(4, coinChange(coins, amount));
    }
}
