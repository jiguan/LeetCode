package com.geeksforgeeks.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FibonacciNum {

	public int nthNum(int n) {
		int prev = 0, cur = 1;
		if (n == 0) {
			return prev;
		}
		for (int i = 2; i <= n; i++) {
			int tmp = prev + cur;
			prev = cur;
			cur = tmp;
		}
		return cur;
	}

	@Test
	public void test() {
		assertTrue(nthNum(6)==8);
	}
	
}