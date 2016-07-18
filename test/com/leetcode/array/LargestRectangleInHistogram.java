package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>(); //store index
        stack.push(-1);
        int max_area = 0;

        for (int i = 0; i < heights.length; ++i) {

            while (stack.peek()!=-1 && heights[stack.peek()] >= heights[i]) {
                // get previous height
                int height = heights[stack.pop()];
                max_area = Math.max(max_area, height * (i - stack.peek() -1));
            }

            stack.push(i);
        }

        return max_area;
    }

    @Test
    public void test0() {
        int[] heights = new int[] {2, 1, 5, 6, 2, 3};
        assertEquals(10, largestRectangleArea(heights));
    }
}
