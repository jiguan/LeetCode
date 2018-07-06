package com.leetcode.stock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int K = 2;
        // Doing 1st/2nd transaction, how much mondey I have at i day
        int[][] dp = new int[K + 1][prices.length];
        int res = 0;
        for (int k = 1; k <= 2; k++) {
            int buy = dp[k - 1][0] - prices[0];
            for (int i = 1; i < prices.length; ++i) {
                // buy on j, sell on i
                // dp[k, i] = max(dp[k][i-1], dp[k-1][j-1] - prices[j] + prices[i]), j=[0..i-1]
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] + buy);
                res = Math.max(res, dp[k][i]);
                buy = Math.max(buy, dp[k - 1][i] - prices[i]);
            }
        }
        return res;
    }

    public int maxProfit0(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int price : prices) {
            sell2 = Math.max(sell2, buy2 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell1 = Math.max(sell1, buy1 + price);
            buy1 = Math.max(buy1, -price);
        }

        return sell2;
    }

    @Test
    public void test0() {
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        assertEquals(6, maxProfit(prices));
    }
}
