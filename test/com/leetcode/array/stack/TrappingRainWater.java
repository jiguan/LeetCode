package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

public class TrappingRainWater {
    public int trap(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            int height = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] < height) {
                int prevHeight = heights[stack.pop()];
                if (!stack.isEmpty()) {
                    int minHeight = Math.min(height, heights[stack.peek()]);
                    res += (minHeight - prevHeight) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] heights = {0, 1, 0, 2};
        assertEquals(1, trap(heights));
    }

    @Test
    public void test1() {
        int[] heights = {2, 1, 0, 1, 3};
        assertEquals(4, trap(heights));
    }

}
