package com.leetcode.stock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k > len / 2) {
            int res = 0;
            for (int i = 0; i < len; i++) {
                if (i > 0 && prices[i] > prices[i - 1]) {
                    res += prices[i] - prices[i - 1];
                }
            }
            return res;
        }

        int[][] buy = new int[len][k + 1];
        int[][] sell = new int[len][k + 1];
        buy[0][0] = -prices[0];
        for (int i = 1; i < len; i++)
            buy[i][0] = Math.max(buy[i - 1][0], -prices[i]);
        for (int j = 1; j <= k; j++)
            buy[0][j] = -prices[0];
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(sell[i - 1][j] - prices[i], buy[i - 1][j]);
                sell[i][j] = Math.max(buy[i - 1][j - 1] + prices[i], sell[i - 1][j]);
            }
        }
        return Math.max(buy[len - 1][k], sell[len - 1][k]);
    }

    @Test
    public void test0() {
        int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        int k = 2;
        assertEquals(13, maxProfit(k, prices));
    }

    @Test
    public void test1() {
        int[] prices = {3, 2, 6, 5, 0, 3};
        int k = 2;
        assertEquals(7, maxProfit(k, prices));
    }
}
