package com.leetcode.stock;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int min = prices[0], max = 0;

        for (int price : prices) {
            min = Math.min(min, price);
            max = Math.max(max, price - min);
        }
        return max;
    }

    @Test
    public void test0() {
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        assertEquals(new Integer(5), new Integer(maxProfit(prices)));
    }
}
