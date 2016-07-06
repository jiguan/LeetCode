package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DecodeWays {
	public int numDecodings(String s) {
		if (s.length() == 0){
		    return 0;
		}
		int[] times = new int[s.length()+1];
		times[0] = 1; //this is for 12, 2 digits are valid, need bonus 
		if(s.charAt(0)!='0') times[1] = 1;
		for(int i=2;i<times.length;i++) {
			if(isWord(i-1, i, s)) {
				times[i] += times[i-1];
			}
			if(isWord(i-2, i, s)) {
				times[i] += times[i-2];
			}
		}
		return times[times.length-1];
	}

	private boolean isWord(int i, int j, String s) {
		if(s.charAt(i)=='0') {
			return false;
		}
		int num = Integer.valueOf(s.substring(i, j));
		if (num > 0 && num <= 26)
			return true;
		return false;
	}
	
	@Test
	public void test0() {
		String s = "12";
		assertEquals(2, numDecodings(s));
	}
	
	@Test
	public void test1() {
		String s = "0";
		assertEquals(0, numDecodings(s));
	}
	
	@Test
	public void test2() {
		String s = "01";
		assertEquals(0, numDecodings(s));
	}
	
	@Test
	public void test3() {
		String s = "1001";
		assertEquals(0, numDecodings(s));
	}
}
