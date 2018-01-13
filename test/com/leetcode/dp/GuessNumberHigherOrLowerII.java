package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GuessNumberHigherOrLowerII {
	public int getMoneyAmount0(int n) {
		int[][] matrix = new int[n + 1][n + 1];
		return dp(matrix, 1, n);
	}

	private int dp(int[][] matrix, int start, int end) {
		if (start >= end) return 0;
		if (matrix[start][end] != 0) return matrix[start][end];

		int res = Integer.MAX_VALUE;
		// start + dp(start + 1, end)
		for (int i = start; i <= end; i++) {
			// the max means whenever you choose a number, the feedback is
			// always bad and therefore leads you to a worse branch
			int worst = i + Math.max(dp(matrix, start, i - 1), dp(matrix, i + 1, end));
			res = Math.min(res, worst);
		}
		matrix[start][end] = res;
		return res;
	}

	public int getMoneyAmount(int n) {
		int[][] cost = new int[n + 2][n + 2];
		for (int len = 1; len < n; ++len) {
			for (int i = 1; i + len <= n; ++i) {
				cost[i][i + len] = Integer.MAX_VALUE;
				for (int j = i; j <= i + len; ++j) {
					cost[i][i + len] = Math.min(cost[i][i + len], Math.max(cost[i][j - 1], cost[j + 1][j]) + j);
				}
			}
		}
		return cost[1][n];
	}

	@Test
	public void test0() {
		assertEquals(1 , getMoneyAmount0(2));
	}

	@Test
	public void test1() {
		assertEquals(16, getMoneyAmount(10));
	}
}
