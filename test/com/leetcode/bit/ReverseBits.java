package com.leetcode.bit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ReverseBits {
	public int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result |= (n & 1) << (31-i);
			n >>= 1;
		}
		return result;
	}

	public int reverseBits0(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result += n & 1;
			n >>>= 1;
			if (i < 31) {
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
