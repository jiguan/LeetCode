package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HouseRobberII {
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int[] zero = new int[nums.length];
		int[] one = new int[nums.length];
		// Rob house 0 to n - 1
		zero[1] = nums[0];
		// Rob house 1 to n
		one[1] = nums[1];
		// i is from 1 to n
		for (int i = 2; i < nums.length; i++) {
			// zero need to minus 1
			zero[i] = Math.max(zero[i - 2] + nums[i - 1], zero[i - 1]);
			one[i] = Math.max(one[i - 2] + nums[i], one[i - 1]);
		}
		return Math.max(zero[nums.length - 1], one[nums.length - 1]);
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 1, 2, 3, 4, 5 };
		assertEquals(8, rob(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[] { 0 };
		assertEquals(0, rob(nums));
	}
}
