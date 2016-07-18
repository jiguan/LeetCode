package com.leetcode.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WaterAndJugProblem {
	public boolean canMeasureWater(int x, int y, int z) {
		if (x + y < z)
			return false;
		if (x == z || y == z || x + y == z)
			return true;
		return z % gcd(x, y) == 0;
	}

	// greatest common divisor
	private int gcd(int a, int b) {
		while (b != 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}

	@Test
	public void testGcd() {
		assertTrue(canMeasureWater(3, 5, 4));
	}

	@Test
	public void test0() {
		assertFalse(canMeasureWater(2, 6, 5));
	}
}
