package com.leetcode.stock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int[] buy = new int[prices.length], sell = new int[prices.length];
        buy[0] = -prices[0];

        for (int i = 1; i < prices.length; ++i) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[sell.length - 1];
    }

    @Test
    public void test0() {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        assertEquals(Integer.valueOf(8), Integer.valueOf(maxProfit(prices, fee)));
    }

    @Test
    public void test1() {
        int[] prices = new int[]{9, 8, 7, 1, 2};
        int fee = 3;
        assertEquals(Integer.valueOf(0), Integer.valueOf(maxProfit(prices, fee)));
    }
}
