package com.leetcode.bit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ReverseBits {
	public int reverseBits(int n) {
		String s = Integer.toBinaryString(n);
		if(s.length()<32) {
			s = new String(new char[32 - s.length()]).replace("\0", "0") + s;
		}
		StringBuffer sb = new StringBuffer(s).reverse();
		char lastDigit = sb.charAt(31);
		sb.delete(31, 32);
		int a = Integer.parseInt(sb.toString(), 2) * 2;
		return a + (lastDigit=='0' ? 0 : 1);
	}
	
	
	public int reverseBits0(int n) {
		int result = 0;
		for(int i=0;i<32;i++) {
			result += n & 1;
			n >>>= 1;
			if(i<31) {
				result <<= 1;
			}
		}
		return result;
	}

	@Test
	public void test0() {
		int n = 43261596;
		assertEquals(reverseBits0(n), reverseBits(n));
	}
	
	@Test
	public void test1() {
		int n = 1;
		assertEquals(Math.abs(reverseBits0(n)), Math.abs(reverseBits(n)));
	}
	

}
