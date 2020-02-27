package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseInteger {
	public int reverse(int x) {
		int res = 0;
		while(x != 0) {
			int tail = x % 10;
			int tmp = res * 10 + tail;
			if ((tmp - tail) / 10 != res) {
				return 0;
			}
			res = tmp;
			x = x / 10;
		}
		return res;
	}
	
	@Test
	public void test0() {
		int x = 120;
		int expect = 21;
		assertEquals(expect, reverse(x));
	}
	
	@Test
	public void test1() {
		int x = -123;
		int expect = -321;
		assertEquals(expect, reverse(x));
	}
	
	@Test
	public void test2() {
		int x = Integer.MIN_VALUE;
		int expect = 0;
		assertEquals(expect, reverse(x));
	}
}
