package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int area = 0;
		while (left < right) {
			area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}
		return area;
	}

	@Test
	public void test0() {
		int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		int expected = 49;
		assertEquals(expected, maxArea(height));
	}
}
