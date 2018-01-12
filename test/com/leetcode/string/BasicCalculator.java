package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicCalculator {
	int i = 0;

	public int calculate(String s) {
		int sign = 1;
		int cur = 0, num = 0;
		while (i < s.length()) {
			char c = s.charAt(i++);
			if (c == '(') {
				num += sign * calculate(s);
			} else if (c == ')') {
				num += sign * cur;
				break;
			} else if (Character.isDigit(c)) {
				cur = cur * 10 + c - '0';
			}

			if (c == '+' || c == '-' || i == s.length()) {
				num += sign * cur;
				cur = 0;
				sign = c == '+' ? 1 : -1;
			}
		}
		return num;
	}

	@Test
	public void test0() {
		String s = "(1+(4+5+2)-3)+(6+8)";
		assertEquals(23, calculate(s));
	}

	@Test
	public void test1() {
		String s = " (1 +(4+15+2)-13)+ (6+8)";
		assertEquals(23, calculate(s));
	}

	@Test
	public void test2() {
		String s = "1";
		assertEquals(1, calculate(s));
	}
}
