package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStockWithCooldowns {
	public int maxProfit(int[] prices) {
		int n = prices.length;
		int[] rest = new int[n];
		int[] sell = new int[n];
		sell[1] = prices[1] - prices[0];
		for (int i = 2; i < n; i++) {
			rest[i] = Math.max(rest[i - 1], sell[i-1]);
			sell[i] = Math.max(rest[i - 2] , sell[i - 1])  + prices[i] - prices[i-1];
		}
		return Math.max(sell[n - 1], rest[n-1]);
	}

	@Test
	public void test0() {
		int[] prices = new int[] { 1, 2, 3, 0, 2 };
		int expected = 3;
		assertEquals(expected, maxProfit(prices));
	}

	@Test
	public void test1() {
		int[] prices = new int[] { 1, 2, 4 };
		int expected = 3;
		assertEquals(expected, maxProfit(prices));
	}

	@Test
	public void test2() {
		int[] prices = new int[] { 1, 4, 2 };
		int expected = 3;
		assertEquals(expected, maxProfit(prices));
	}

	@Test
	public void test3() {
		int[] prices = new int[] { 2, 4, 1 };
		int expected = 2;
		assertEquals(expected, maxProfit(prices));
	}
}
