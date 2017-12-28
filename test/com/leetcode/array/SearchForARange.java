package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SearchForARange {
	public int[] searchRange(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		int[] res = new int[] { -1, -1 };
		if (nums.length == 0)
			return res;

		while (start < end) {
			int mid = (end - start) / 2 + start;
			if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		if (nums[start] != target)
			return res;
		else
			res[0] = start;

		end = nums.length - 1;
		while (start < end) {
			int mid = (end - start) + 1 / 2 + start;
			if (nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid;
			}
		}
		res[1] = end;
		return res;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 5, 7, 7, 8, 8, 10 };
		int[] res = searchRange(nums, 8);
		assertArrayEquals(new int[] { 3, 4 }, res);
	}

	@Test
	public void test1() {
		int[] nums = new int[] { 1 };
		int[] res = searchRange(nums, 0);
		assertArrayEquals(new int[] { -1, -1 }, res);
	}

	@Test
	public void test2() {
		int[] nums = new int[] { 1 };
		int[] res = searchRange(nums, 1);
		assertArrayEquals(new int[] { 0, 0 }, res);
	}

	@Test
	public void test3() {
		int[] nums = new int[] {};
		int[] res = searchRange(nums, 0);
		assertArrayEquals(new int[] { -1, -1 }, res);
	}
}
