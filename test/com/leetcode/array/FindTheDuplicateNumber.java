package com.leetcode.array;

import org.junit.Test;

public class FindTheDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int low = 1, high = nums.length-1;
		while(low<high) {
			int mid = (high - low) / 2 + low;
			int count = 0;
			for(int num : nums) {
				if(num<=mid) count++;
			}
			if(count<=mid) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 3, 4, 2, 1, 2 };
		System.out.println(findDuplicate(nums));
	}
}
