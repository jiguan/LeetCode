package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExcelSheetColumnTitle {
	public String convertToTitle(int n) {
		StringBuffer buffer = new StringBuffer();
		while(n!=0) {
			char c = (char) ((n-1) % 26 + 'A');
			buffer.append(c);
			n = (n-1)/26;
		}
		return buffer.reverse().toString();
	}
	
	@Test
	public void test0() {
		int n = 1;
		assertEquals("A", convertToTitle(n));
	}
	
	@Test
	public void test1() {
		int n = 26;
		assertEquals("Z", convertToTitle(n));
	}
	
	@Test
	public void test2() {
		int n = 27;
		assertEquals("AA", convertToTitle(n));
	}
}
