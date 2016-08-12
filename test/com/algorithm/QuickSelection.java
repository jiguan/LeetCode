package com.algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuickSelection {
	public void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public int partition(int[] arr, int start, int end) {
		int pivotValue = arr[end];
		int j = start;
		for (int i = start; i < end; i++) {
			if (arr[i] > pivotValue) {
				swap(arr, i, j);
				j++;
			}
		}
		swap(arr, j, end);
		return j;

	}

	public int quickSelect(int[] nums, int start, int end, int k) {
		if (start == end)
			return nums[start];
		int pivot = partition(nums, start, end);
		if (pivot == k)
			return nums[k];
		else if (pivot < k) {
			return quickSelect(nums, pivot + 1, end, k);
		} else {
			return quickSelect(nums, start, pivot - 1, k);
		}
		
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{1,2,3,4,5,6};
		assertEquals(5, quickSelect(nums, 0, nums.length-1, 1));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{1,2,3,4,5,6};
		assertEquals(4, quickSelect(nums, 0, nums.length-1, 2));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{1};
		assertEquals(1, quickSelect(nums, 0, nums.length-1, 1));
	}
}
