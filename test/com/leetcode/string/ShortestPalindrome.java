package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShortestPalindrome {
	public String shortestPalindrome(String s) {
		int start = 0;
		for (int end = s.length() - 1; end >= 0; end--) {
			if (s.charAt(start) == s.charAt(end)) {
				start++;
			}
		}

		if (start == s.length())
			return s;
		String suffix = s.substring(start);
		return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, start)) + suffix;
	}
	
	@Test
	public void test0() {
		String s = "aacecaaa";
		assertEquals("aaacecaaa", shortestPalindrome(s));
	}
	
	@Test
	public void test1() {
		String s = "gxybakbkabbfmbnnnjjjyxqg";
		assertEquals("aaacecaaa", shortestPalindrome(s));
	}
}
