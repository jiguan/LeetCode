package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExcelSheetColumnNumber {
	public int titleToNumber(String s) {
		char[] letters = s.toCharArray();
		int result = 0;
		for(char letter : letters) {
			result *= 26;
			result += letter-'A'+1;
		}
		return result;
	}
	
	@Test
	public void test0() {
		String s = "AB";
		assertEquals(28, titleToNumber(s));
	}
}
