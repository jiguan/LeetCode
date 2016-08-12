package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringToInteger {
	public int myAtoi(String str) {
		int result = 0, i=0, sign = 1;
		if(str.length()==0) return result;
		
		char[] arr = str.toCharArray();
		while(arr[i]==' ') i++;
		
		if(arr[i]=='-'||arr[i]=='+') {
			sign = arr[i++] == '-' ? -1 : 1;
		}
		for(;i<str.length();i++) {
			char c = arr[i];
			int tmp = c - '0';
			if(tmp<0 || tmp>9) break;
			if(sign == -1 && (result > Integer.MIN_VALUE / -10 || (result == Integer.MIN_VALUE / -10 && tmp > 8))){
				return Integer.MIN_VALUE;
			}
			if(sign==1&& (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && tmp > 7))) {
				return Integer.MAX_VALUE;
			}
			result = result * 10 + tmp;
		}
		return result * sign;
	}
	
	@Test
	public void test0() {
		String str = "10";
		assertEquals(10, myAtoi(str));
	}
	
	@Test
	public void test1() {
		String str = "0";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test2() {
		String str = "-10";
		assertEquals(-10, myAtoi(str));
	}
	
	@Test
	public void test3() {
		String str = "-0";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test5() {
		String str = "+";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test4() {
		String str = "";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test6() {
		String str = "+100";
		assertEquals(100, myAtoi(str));
	}
	
	@Test
	public void test7() {
		String str = "+-2";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test8() {
		String str = "2147483648";
		assertEquals(Integer.MAX_VALUE, myAtoi(str));
	}
	
	@Test
	public void test9() {
		String str = "-2147483649";
		assertEquals(Integer.MIN_VALUE, myAtoi(str));
	}
	
}
