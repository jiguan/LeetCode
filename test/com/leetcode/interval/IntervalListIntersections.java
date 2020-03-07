package com.leetcode.interval;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int[] a = A[i], b = B[j];
            int start = Math.max(a[0], b[0]);
            int end = Math.min(a[1], b[1]);
            if (start <= end) {
                res.add(new int[] {start, end});
            }
            if (a[1] == end) ++i;
            if (b[1] == end) ++j;
        }
        return res.toArray(new int[res.size()][2]);
    }

    @Test
    public void test0() {
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] expected = {{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}};
        assertArrayEquals(expected, intervalIntersection(A, B));
    }
}
