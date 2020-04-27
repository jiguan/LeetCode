package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

public class ConstrainedSubsetSum {
	public int constrainedSubsetSum(int[] nums, int k) {
		int res = nums[0];
		int[] sum = new int[nums.length];
		// Store the index sum array for current largest sum
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < nums.length; ++i) {
			// add the largest sum of subsets
			sum[i] = nums[i] + (!q.isEmpty() ? sum[q.peek()] : 0);
			res = Math.max(res, sum[i]);
			// keep queue descending order
			while (!q.isEmpty() && sum[i] > sum[q.peekLast()]) {
				q.pollLast();
			}

			// distance > k, discard current sum
			if (i >= k && !q.isEmpty() && i + 1 - q.peekFirst() > k) {
				q.pollFirst();
			}
			if (sum[i] > 0) {
				q.offer(i);
			}
		}
		return res;
	}

	@Test
	public void test0() {
		int[] nums = { 10, 2, -10, 5, 20 };
		int k = 2;
		assertEquals(Integer.valueOf(37), Integer.valueOf(constrainedSubsetSum(nums, k)));
	}

	@Test
	public void test1() {
		int[] nums = { -5266, 4019, 7336, -3681, -5767 };
		int k = 2;
		assertEquals(Integer.valueOf(11355), Integer.valueOf(constrainedSubsetSum(nums, k)));
	}

	@Test
	public void test2() {
		int[] nums = { -10, -1, -2, -3, -5 };
		int k = 2;
		assertEquals(Integer.valueOf(-1), Integer.valueOf(constrainedSubsetSum(nums, k)));
	}
}
