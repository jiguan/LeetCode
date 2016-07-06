package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MultiplyStrings {
	public String multiply(String num1, String num2) {
		int l1 = num1.length(), l2 = num2.length();
		int[] result = new int[l1+l2];
		for(int i = l1-1;i>=0;i--) {
			for(int j = l2-1;j>=0;j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int p1 = i+j, p2 = i+j+1;
				int sum = mul + result[p2];
				result[p1] += sum / 10;
				result[p2] = sum % 10;
			}
		}
		StringBuffer sb = new StringBuffer();
		for(int i : result) {
			if(!(sb.length()==0&&i==0)) sb.append(i);
		}
		return sb.toString();
	}
		
	@Test
	public void test0() {
		String num1 = "123", num2 = "1";
		assertEquals("123", multiply(num1, num2));
	}
	
	
	@Test
	public void test2() {
		String num1 = "123", num2 = "456";
		assertEquals("56088", multiply(num1, num2));
	}
	
	
}
