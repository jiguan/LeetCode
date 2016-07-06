package com.leetcode.bit;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class CountingBits {
	public int[] countBits(int num) {
		int[] arr = new int[num+1];
		arr[0] = 0;
		for(int i=1;i<=num;i++) {
			// a little bit of DP, i & (i-1) remove the last 1, arr[i&(i-1)} to get how many 1
			arr[i] = arr[ i& (i-1) ] +1;
		}
		return arr;
	}
	
	@Test
	public void test0() {
		int num = 5;
		int[] expected = new int[]{0,1,1,2,1,2};
		assertArrayEquals(expected, countBits(num));
	}
	
	@Test
	public void test1() {
		int num = 8;
		int[] expected = new int[]{0,1,1,2,1,2,2,3,1};
		assertArrayEquals(expected, countBits(num));
	}
}
