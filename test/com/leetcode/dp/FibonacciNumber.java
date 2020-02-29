package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FibonacciNumber {
	public int fib0(int N) {
		int[] dp = new int[N + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= N; ++i) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[N];
	}

	public int fib(int N) {
		if (N == 0)
			return 0;
		int ppre = 0, pre = 1;
		for (int i = 2; i <= N; ++i) {
			int res = pre + ppre;
			ppre = pre;
			pre = res;
		}
		return pre;
	}

	@Test
	public void test0() {
		int N = 3;
		assertEquals(2, fib(N));
	}
}
