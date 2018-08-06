package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MaxProductNElement {
	public int maxProduct(int[] array, int n) {
		Arrays.sort(array);
		List<Integer> nums = new ArrayList<>();
		for (int num : array) {
			nums.add(num);
		}
		int res = 1;
		if (n % 2 == 1) {
			res = nums.remove(nums.size() - 1);
			n--;
		}
		return res * maxProduct(nums, n);
	}

	private int maxProduct(List<Integer> nums, int n) {
		if (n == 0) return 1;
		int res = 1;
		int p1 = nums.get(0) * nums.get(1);
		int p2 = nums.get(nums.size() - 1) * nums.get(nums.size() - 2);
		if (p1 > p2) {
			nums.remove(0);
			nums.remove(0);
			res = p1 * maxProduct(nums, n - 2);
		} else {
			nums.remove(nums.size() - 1);
			nums.remove(nums.size() - 1);
			res = p2 * maxProduct(nums, n - 2);
		}
		return res;
	}

	@Test
	public void test0() {
		int[] array = new int[] { 8, 6, 9, 0, -2 };
		int n = 2;
		assertEquals(72, maxProduct(array, n));
	}

	@Test
	public void test1() {
		int[] array = new int[] { 8, 6, 9, 0, -20, -10 };
		int n = 2;
		assertEquals(200, maxProduct(array, n));
	}

	@Test
	public void test2() {
		int[] array = new int[] { 8, 5, 9, 0, -2, -10 };
		int n = 3;
		assertEquals(360, maxProduct(array, n));
	}
	
	@Test
	public void test3() {
		int[] array = new int[] { 8, 5, 9, 1, 0, -2, -10, -1 };
		int n = 4;
		assertEquals(1440, maxProduct(array, n));
	}
}
