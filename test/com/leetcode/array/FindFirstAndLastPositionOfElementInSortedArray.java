package com.leetcode.array;

import org.junit.Test;

import com.leetcode.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return new int[] { -1, -1 };
		}
		int low = 0, high = nums.length - 1;
		int[] res = new int[2];
		while (low < high) {
			int mid = (high - low) / 2 + low;
			if (target > nums[mid]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		if (target == nums[low]) {
			res[0] = low;
		} else {
			res[0] = -1;
		}

		high = nums.length - 1;
		while (low < high) {
			int mid = (high - low) / 2 + low + 1;
			if (target < nums[mid]) {
				high = mid - 1;
			} else {
				low = mid;
			}
		}

		if (target == nums[high]) {
			res[1] = high;
		} else {
			res[1] = -1;
		}
		return res;
	}

	@Test
	public void test0() {
		int[] nums = { 1 };
		int target = 0;
		int[] expected = { -1, -1 };
		Arrays.assertEquals(expected, searchRange(nums, target));
	}

}
