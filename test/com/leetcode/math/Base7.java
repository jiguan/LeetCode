package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Base7 {
	public String convertToBase7(int num) {
		if (num < 0)
			return "-" + convertToBase7(-num);
		if (num < 7) {
			return String.valueOf(num);
		}
		return convertToBase7(num / 7) + num % 7;
	}

	@Test
	public void test0() {
		assertEquals("202", convertToBase7(100));
	}

	@Test
	public void test1() {
		assertEquals("-10", convertToBase7(-7));
	}
}
