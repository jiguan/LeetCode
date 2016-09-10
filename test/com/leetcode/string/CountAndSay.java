package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountAndSay {
	public String countAndSay(int n) {
		if (n <= 0)
			return "0";
		String num = "1";
		for (int i = 1; i < n; i++) {
			num = getNext(num);
		}
		return num;
	}

	private String getNext(String num) {
		StringBuffer buffer = new StringBuffer();
		char current = num.charAt(0);
		int count = 1;
		for (int i = 1; i < num.length(); i++) {
			if (num.charAt(i) == current) {
				count++;
			} else {
				buffer.append(count);
				buffer.append(current);
				current = num.charAt(i);
				count = 1;
			}
		}
		buffer.append(count);
		buffer.append(current);
		return buffer.toString();
	}

	@Test
	public void test0() {
		int n = 1;
		assertEquals("1", countAndSay(n));
	}

	@Test
	public void test1() {
		int n = 2;
		assertEquals("11", countAndSay(n));
	}

	@Test
	public void test2() {
		int n = 5;
		assertEquals("111221", countAndSay(n));
	}
}
