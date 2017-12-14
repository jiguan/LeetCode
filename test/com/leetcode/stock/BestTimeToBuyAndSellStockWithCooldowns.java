package com.leetcode.stock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStockWithCooldowns {
    public int maxProfit0(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        int n = prices.length;
        int[] buy = new int[n + 1];
        int[] sell = new int[n + 1];

        buy[1] = -prices[0];
        for (int i = 2; i <= n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
        }
        return sell[n];
    }
    
    public int maxProfit(int[] prices) {
        int prev_buy = 0, buy = -prices[0], prev_sell = 0, sell = 0;
        for(int price : prices) {
            prev_buy = buy;
            // Here prev_sell is actually i - 2, since sell is i - 1 at this moment
            buy = Math.max(prev_buy, prev_sell - price);
            prev_sell = sell;
            sell = Math.max(prev_sell, prev_buy + price);
        }
        return sell;
    }

    @Test
    public void test0() {
        int[] prices = new int[]{1, 2, 3, 0, 2};
        int expected = 3;
        assertEquals(expected, maxProfit(prices));
    }

    @Test
    public void test1() {
        int[] prices = new int[]{1, 2, 4};
        int expected = 3;
        assertEquals(expected, maxProfit(prices));
    }

    @Test
    public void test2() {
        int[] prices = new int[]{1, 4, 2};
        int expected = 3;
        assertEquals(expected, maxProfit(prices));
    }

    @Test
    public void test3() {
        int[] prices = new int[]{2, 4, 1};
        int expected = 2;
        assertEquals(expected, maxProfit(prices));
    }
}
