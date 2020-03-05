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
	    int count = 0;
        char prev = num.charAt(0);
        StringBuilder sb = new StringBuilder();
        for(char ch : num.toCharArray()) {
            if(prev == ch) {
                count++;
            } else {
                sb.append(count);
                sb.append(prev);
                count = 1;
                prev = ch;
            }
        }
        sb.append(count);
        sb.append(prev);
        return sb.toString();
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
    public void test3() {
        int n = 4;
        assertEquals("1211", countAndSay(n));
    }
	
	@Test
	public void test2() {
		int n = 5;
		assertEquals("111221", countAndSay(n));
	}
}
