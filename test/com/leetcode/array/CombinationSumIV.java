package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CombinationSumIV {
	public int combinationSum4(int[] nums, int target) {
		if (target <= 0) {
			return target == 0 ? 1 : 0;
		}

		int[] dp = new int[target + 1];
		// result is 0, one combo found
		dp[0] = 1;
		for (int i = 1; i < dp.length; ++i) {
			for (int j = 0; j < nums.length; ++j) {
				if (i - nums[j] >= 0) {
					dp[i] += dp[i - nums[j]];
				}
			}
		}
		return dp[target];
	}

	public int combinationSum40(int[] nums, int target) {
		if (nums == null || nums.length == 0) return 0;
		int[] dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;

		return traverse(nums, target, dp);
	}

	private int traverse(int[] nums, int target, int[] dp) {
		if (target < 0) return 0;

		if (dp[target] != -1) {
			return dp[target];
		}

		int res = 0;
		for (int i = 0; i < nums.length; ++i) {
			res += traverse(nums, target - nums[i], dp);
		}
		dp[target] = res;
		return res;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 1, 2, 3 };
		int target = 4;
		assertEquals(7, combinationSum40(nums, target));
	}

	@Test
	public void test1() {
		int[] nums = new int[] { 1, 2, 3 };
		int target = 32;
		assertEquals(181997601, combinationSum4(nums, target));
	}
}
