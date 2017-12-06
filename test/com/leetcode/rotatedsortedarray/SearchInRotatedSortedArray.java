package com.leetcode.rotatedsortedarray ;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchInRotatedSortedArray {
	public int search0(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			int median = nums[mid];
			if (median == target)
				return mid;
			if (median > nums[high]) { // first half sorted
				if (nums[low] <= target && target < median) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else { // second half sorted
				if (median < target && target <= nums[high]) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		return -1;
	}

	public int search1(int[] nums, int target) {
		if (nums.length == 0)
			return -1;
		int start = 0, end = nums.length - 1;

		while (start < end) {
			int mid = (end - start) / 2 + start;
			if (nums[mid] == target)
				return mid;
			if (nums[start] == target)
				return start;
			if (nums[end] == target)
				return end;

			if (nums[start] < nums[mid]) {
				if (nums[start] < target && target < nums[mid]) {
					end = mid - 1;
				} else {
					start = mid;
				}
			} else {
				if (nums[mid] < target && target < nums[end]) {
					start = mid + 1;
				} else {
					end = mid;
				}
			}
		}
		return nums[start] == target ? start : -1;
	}
	
	public int search(int[] nums, int target) {
		int start = 0, end = nums.length -1;
		
		// Find the index of the smallest element.
		while(start < end) {
			int mid = (end - start) / 2 + start;
			if(nums[mid] > nums[end]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		int rot = start;
		start = 0;
		end = nums.length - 1;
		while(start <= end) {
			int mid = (end - start) / 2 + start;
			int realMid = (mid + rot) % nums.length;
			if(nums[realMid] > target) {
				end = mid - 1;
			} else if(nums[realMid] < target) {
				start = mid + 1;
			} else {
				return realMid;
			}
		}
		return -1;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 5, 6, 7, 8, 9, 1, 2, 3, 4 };
		assertEquals(1, search(nums, 6));
		assertEquals(7, search(nums, 3));
		assertEquals(-1, search(nums, 0));
	}

	@Test
	public void test1() {
		int[] nums = new int[] { 3, 4, 5, 6, 7, 8, 9, 1, 2 };
		assertEquals(7, search(nums, 1));
		assertEquals(2, search(nums, 5));
	}

	@Test
	public void test2() {
		int[] nums = new int[] { 7, 8, 9, 1, 2, 3, 4, 5, 6 };
		// assertEquals(2, search(nums, 9));
		assertEquals(5, search(nums, 3));
	}

	@Test
	public void test3() {
		int[] nums = new int[] { 7 };
		assertEquals(0, search(nums, 7));
		assertEquals(-1, search(nums, 8));
	}

	@Test
	public void test4() {
		int[] nums = new int[] { 1, 3 };
		assertEquals(1, search(nums, 3));
		assertEquals(0, search(nums, 1));
	}

	@Test
	public void test5() {
		int[] nums = new int[] { 3, 1 };
		assertEquals(1, search(nums, 1));
		assertEquals(0, search(nums, 3));
	}

}
