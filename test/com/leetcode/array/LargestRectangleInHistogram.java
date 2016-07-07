package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class LargestRectangleInHistogram {
	public int largestRectangleArea(int[] heights) {

		Stack<Integer> stack = new Stack<Integer>();
		int max_area = 0;

		for (int i = 0; i <= heights.length; ++i) {
			int height_bound = (i == heights.length) ? 0 : heights[i];

			while (!stack.isEmpty()) {
				//get previous height
				int h = heights[stack.peek()];

				// calculate the area for every ascending slope.
				if (h < height_bound) {
					break;
				}
				stack.pop();

				// at the end, the area with the height of the minimal element.
				int index_bound = stack.isEmpty() ? -1 : stack.peek();
				max_area = Math.max(max_area, h * ((i - 1) - index_bound));
			}

			stack.push(i);
		}

		return max_area;
	}

	@Test
	public void test0() {
		int[] heights = new int[] { 2, 1, 5, 6, 2, 3 };
		assertEquals(10, largestRectangleArea(heights));
	}
}
