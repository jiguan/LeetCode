package com.leetcode.array.twopointer;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LargestRectangleInHistogramBruteForce {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            int width = expand(heights, i);
            res = Math.max(res, width * heights[i]);
        }
        return res;

    }

    private int expand(int[] heights, int index) {
        int left = index;
        int right = index;

        while (left > 0 && heights[left - 1] >= heights[index]) {
            left--;
        }
        while (right < heights.length -1 && heights[right + 1] >= heights[index]) {
            right++;
        }

        return right - left + 1;
    }

    @Test
    public void test0() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int expected = 10;
        assertEquals(expected, largestRectangleArea(heights));
    }
}
