package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/*
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
	Solve it without division and in O(n).
	For example, given [1,2,3,4], return [24,12,8,6].
	Follow up:
	Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */

/*
Numbers:     2    3    4     5
Lefts:            2  2*3 2*3*4
Rights:  3*4*5  4*5    5      

Let's fill the empty with 1:

Numbers:     2    3    4     5
Lefts:       1    2  2*3 2*3*4
Rights:  3*4*5  4*5    5     1
 */

public class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		// need to set to 1, otherwise when traverse from right to left, res[0] is 0
		res[0] = 1;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}
		int right = nums[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			res[i] *= right;
			right = right * nums[i];
		}
		return res;
	}

	@Test
	public void test0() {
		int[] arr = new int[] { 1, 2, 3, 4 };
		int[] actual = productExceptSelf(arr);
		int[] expected = new int[] { 24, 12, 8, 6 };
		assertTrue(Arrays.equals(expected, actual));
	}

	@Test
	public void test1() {
		int[] arr = new int[] { 4, 3, 2, 1, 2 };
		int[] expected = new int[] { 12, 16, 24, 48, 24 };
		int[] actual = productExceptSelf(arr);
		assertTrue(Arrays.equals(expected, actual));
	}

	@Test
	public void test2() {
		int[] arr = new int[] { 1, 0 };
		int[] expected = new int[] { 0, 1 };
		int[] actual = productExceptSelf(arr);
		assertTrue(Arrays.equals(expected, actual));
	}
}
