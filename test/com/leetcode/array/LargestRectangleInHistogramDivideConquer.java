package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LargestRectangleInHistogramDivideConquer {
    public int largestRectangleArea(int[] heights) {
        return partition(0, heights.length-1, heights);
    }

    private int partition(int start, int end, int[] heights) {
        if (start == end)
            return heights[start];
        int mid = (end - start) / 2 + start;
        int area = partition(start, mid, heights);
        area = Math.max(area, partition(mid + 1, end, heights));
        area = Math.max(area, maxArea(start, mid, end, heights));
        return area;
    }

    private int maxArea(int start, int mid, int end, int[] heights) {
        //start from mid and extend to boundary
        int i = mid, j = mid + 1;
        int area = 0, h = Math.max(heights[i], heights[j]);
        while(start<=i && j<=end) {
            h = Math.min(h, Math.min(heights[i], heights[j]));
            area = Math.max(area, (j-i+1) * h);
            if(i==start) j++;
            else if(j==end) i--;
            else {
                //left is higher than right
                if(heights[i-1] > heights[j+1]) i--;
                else j++;
            }
        }
        return area;
    }
    
    @Test
    public void test0() {
        int[] heights = new int[] {2, 1, 5, 6, 2, 3};
        assertEquals(10, largestRectangleArea(heights));
    }
}
