package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>(); // store index
        stack.push(-1);
        int maxArea = 0;

        for (int i = 0; i <= heights.length; ++i) {
            int currHeight = (i != heights.length) ? heights[i] : 0;

            // currHeight is smaller than the prev one
            // pop all indexes from stack whose value is larger than currHeight
            while (stack.peek() != -1 && heights[stack.peek()] >= currHeight) {
                // get previous height
                int height = heights[stack.pop()];
                // we need to use stack.peek where indicates the starting index
                maxArea = Math.max(maxArea, height * (i - stack.peek() - 1));
            }

            stack.push(i);
        }
        return maxArea;
    }

    @Test
    public void test0() {
        //int[] heights = new int[] {2, 1, 5, 6, 2, 3};
        //assertEquals(10, largestRectangleArea(heights));
    }

    @Test
    public void test1() {
        int[] heights = new int[] {2,0, 3, 2};
        assertEquals(4, largestRectangleArea(heights));
    }
}
