package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FactorialTrailingZeroes {
	public int trailingZeroes(int n) {
		int num = 0;
		while(n!=0) {
			num = num + n/5;
			n = n/5;
		}
		return num;
	}
	
	@Test
	public void test0() {
		int n =20;
		assertEquals(4, trailingZeroes(n));
	}
	
	@Test
	public void test1() {
		int n =25;
		assertEquals(6, trailingZeroes(n));
	}
}
