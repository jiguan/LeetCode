package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

// https://leetcode.com/discuss/30527/three-way-solve-this-problem-the-first-way-interesting-java
public class RotateArray {
	public void rotate(int[] nums, int k) {
		int len = nums.length;
		k %= len;
		int times = 0;
		for (int i = 0; i < k; i++) {
			if (times == len)
				return;
			int index = next(i, k, len);
			int replaced = nums[i];
			while (index != i) {
				nums[index] ^= replaced;
				replaced ^= nums[index];
				nums[index] ^= replaced;
				index = next(index, k, len);
				times++;
			}
			nums[index] = replaced;
			times++;
		}
	}

	private int next(int current, int k, int len) {
		return (current + k) % len;
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int tmp = nums[start];
			nums[start] = nums[end];
			nums[end] = tmp;
			start++;
			end--;
		}
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		int k = 3;
		rotate(nums, k);
		assertArrayEquals(new int[] { 6, 7, 8, 1, 2, 3, 4, 5 }, nums);

	}

	@Test
	public void test1() {
		int[] nums = new int[] { 1, 2, 3 };
		int k = 1;
		rotate(nums, k);
		assertArrayEquals(new int[] { 3, 1, 2 }, nums);
	}

	@Test
	public void test2() {
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6 };
		int k = 2;
		rotate(nums, k);
		assertArrayEquals(new int[] { 5, 6, 1, 2, 3, 4 }, nums);
	}

	@Test
	public void test3() {
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int k = 3;
		rotate(nums, k);
		assertArrayEquals(new int[] { 7, 8, 9, 1, 2, 3, 4, 5, 6 }, nums);
	}
}
