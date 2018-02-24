package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        // distance - times
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                int distance = getDistance(points[i], points[j]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (int val : map.values()) {
                res += val * (val - 1);
            }
            map.clear();
        }
        return res;
    }

    private int getDistance(int[] p1, int[] p2) {
        int x = p1[0] - p2[0];
        int y = p1[1] - p2[1];
        return x * x + y * y;
    }

    @Test
    public void test0() {
        int[][] points = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        assertEquals(Integer.valueOf(2), Integer.valueOf(numberOfBoomerangs(points)));
    }

    @Test
    public void test1() {
        int[][] points = new int[][]{{1, 1}, {2, 2}, {3, 3}};
        assertEquals(Integer.valueOf(2), Integer.valueOf(numberOfBoomerangs(points)));
    }
}
