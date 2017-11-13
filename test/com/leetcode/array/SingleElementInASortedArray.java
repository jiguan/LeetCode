package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingleElementInASortedArray {

	public int singleNonDuplicate1(int[] nums) {
		// it doesn't mean the target number is in the range of low and high
		int low = 0, high = nums.length / 2;
		while (low < high) {
			int mid = (high - low) / 2 + low;
			// 2 * m is another type of high
			// 2 * m is supposed to be the first element of a pair
			if (nums[2 * mid] != nums[2 * mid + 1]) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return nums[2 * low];
	}
	
	public int singleNonDuplicate(int[] nums) {
		int low = 0, high = nums.length - 1;
		while(low < high) {
			int mid = (high - low) / 2 + low;
			// find mid's partner, if mid is odd, then partner is -1; otherwise +1
			if(nums[mid] == nums[mid ^ 1]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return nums[low];
	}

	@Test
	public void test0() {
		int[] num = { 1, 1, 2, 3, 3 };
		assertEquals(2, singleNonDuplicate(num));
	}

	@Test
	public void test1() {
		int[] num = { 1, 1, 2, 2, 3, 4, 4 };
		assertEquals(3, singleNonDuplicate(num));
	}
}
