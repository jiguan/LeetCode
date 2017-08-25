package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArithmeticSlices {
	public int numberOfArithmeticSlices(int[] A) {
		int current = 0, result = 0;
		for (int i = 2; i < A.length; ++i) {
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
				current += 1;
				result += current;
			} else {
				current = 0;
			}
		}
		return result;
	}

	@Test
	public void test0() {
		int[] num = { 1, 2, 3, 4 };
		assertEquals(3, numberOfArithmeticSlices(num));
	}

	@Test
	public void test1() {
		int[] num = { 1, 2, 3, 8, 9, 10 };
		assertEquals(2, numberOfArithmeticSlices(num));
	}
	

	@Test
	public void test2() {
		int[] num = { 2, 4, 6, 8, 10 };
		assertEquals(6, numberOfArithmeticSlices(num));
	}
	
	
}
