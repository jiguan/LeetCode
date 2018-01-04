package com.leetcode.stock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int[] buy = new int[prices.length + 1], sell = new int[prices.length + 1];
        buy[0] = - prices[0];

        for (int i = 0; i < prices.length; ++i) {
            buy[i + 1] = Math.max(buy[i], sell[i] - prices[i]);
            sell[i + 1] = Math.max(sell[i], buy[i] + prices[i] - fee);
        }
        return sell[sell.length - 1];
    }

    @Test
    public void test0() {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        assertEquals(8, maxProfit(prices, fee));
    }
}
