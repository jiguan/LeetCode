package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConvertANumberToHexadecimal {
	public String toHex(int num) {
		if(num == 0) return "0";
		char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		String res = "";
		while (num != 0) {
			res = map[num & 15] + res;
			num = num >>> 4;
		}
		return res;
	}
	
	@Test
	public void test0() {
		assertEquals("1a", toHex(26));
	}
	
	@Test
	public void test1() {
		assertEquals("ffffffff", toHex(-1));
	}
	
	@Test
	public void test2() {
		assertEquals("0", toHex(0));
	}
}
