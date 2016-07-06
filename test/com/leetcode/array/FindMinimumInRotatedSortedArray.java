package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
		int start = 0, end = nums.length - 1;
		while(start<end) {
			int mid = start + (end - start) / 2;
			if(nums[end] > nums[mid]) {
				end = mid;
			} else {
				start = mid+1;
			}
		}
		return nums[start];
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{0,1,2,3,4,5};
		assertEquals(0, findMin(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{2,3,4,5,0,1};
		assertEquals(0, findMin(nums));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{4,5,0,1,2,3};
		assertEquals(0, findMin(nums));
	}
	
}
