package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddString {
	public String addStrings(String num1, String num2) {
		int len1 = num1.length(), len2 = num2.length();
		StringBuffer buffer = new StringBuffer();

		int carry = 0;
		for (int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0 || carry != 0; --i, --j) {
			int add_res = carry + (i < 0 ? 0 : num1.charAt(i) - '0') + (j < 0 ? 0 : num2.charAt(j) - '0');
			carry = add_res / 10;
			buffer.append(add_res % 10);
		}
		return buffer.reverse().toString();
	}

	@Test
	public void test0() {
		String num1 = "2123", num2 = "98765";
		assertEquals("100888", addStrings(num1, num2));
	}

	@Test
	public void test1() {
		String num1 = "0", num2 = "0";
		assertEquals("0", addStrings(num1, num2));
	}
}
