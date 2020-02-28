package com.leetcode.stock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStock {
	// price cannot be negative
	public int maxProfit(int[] prices) {
		int current = 0, profit = 0;
		for (int i = 1; i < prices.length; i++) {
			// continuously hold it
			current = Math.max(0, current + (prices[i] - prices[i - 1]));
			profit = Math.max(profit, current);
		}
		return profit;
	}

	public int maxProfitStraightForward(int[] prices) {
		if (prices.length < 2)
			return 0;
		int min = prices[0], max = prices[0];
		int profit = 0;

		for (int i = 1; i < prices.length; i++) {
			int price = prices[i];
			if (price < min) {
				min = price;
				max = price;
			} else if (price > max) {
				max = price;
				profit = Math.max(profit, max - min);
			}
		}
		return profit;
	}

	@Test
	public void test0() {
		int[] prices = new int[] { 7, 1, 5, 3, 6, 4 };
		assertEquals(new Integer(5), new Integer(maxProfit(prices)));
	}
}
