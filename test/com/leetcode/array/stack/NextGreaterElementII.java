package com.leetcode.array.stack;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

//  The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
public class NextGreaterElementII {
	// Time O(N) for one pass
	// Spce O(N) in worst case
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		Arrays.fill(res, -1);
		// stack only store index decreasing number
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n * 2; ++i) {
			int num = nums[i % n];
			// encounter a large number, set all pending slot to this value
			while (!stack.isEmpty() && nums[stack.peek()] < num) {
				res[stack.pop()] = num;
			}
			if (i < n)
				stack.push(i % n);
		}
		return res;
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 1, 2, 1 };
		int[] expected = new int[] { 2, -1, 2 };
		com.leetcode.util.Arrays.assertEquals(expected, nextGreaterElements(nums));
	}

	@Test
	public void test1() {
		int[] nums = new int[] { 4, 3, 2 };
		int[] expected = new int[] { -1, 4, 4 };
		com.leetcode.util.Arrays.assertEquals(expected, nextGreaterElements(nums));
	}
}
