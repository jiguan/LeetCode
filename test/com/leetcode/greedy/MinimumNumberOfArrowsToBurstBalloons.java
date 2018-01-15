package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int arrow = points[0][1];
        int res = 1;
        
        for(int i = 1;i<points.length;++i) {
            // current arrow can burst this balloon
            if(arrow >= points[i][0]) {
                continue;
            }
            arrow = points[i][1];
            res++;
        }
        return res;
    }

    @Test
    public void test0() {
        int[][] points = new int[][] { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } };
        // shoot 6, shoot 12
        assertEquals(2, findMinArrowShots(points));
    }
}
