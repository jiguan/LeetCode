package com.interview.apple;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MergeKIntervalListIntersections {
    public int[][] intervalIntersection(List<int[][]> intervals) {
        if (intervals.isEmpty()) return new int[0][2];
        return partition(intervals, 0, intervals.size() - 1);
    }

    private int[][] partition(List<int[][]> intervals, int start, int end) {
        if (start == end) return intervals.get(start);
        int mid = start + (end - start) / 2;
        int[][] p1 = partition(intervals, start, mid);
        // only if mid == end, mid + 1 > end
        // but when mid == end? only if start == end
        int[][] p2 = partition(intervals, mid + 1, end);
        return merge(p1, p2);
    }

    private int[][] merge(int[][] A, int[][] B) {
        if (A.length == 0) return B;
        if (B.length == 0) return A;
        int i = 0, j = 0;
        List<int[]> res = new ArrayList<int[]>();
        while (i < A.length && j < B.length) {
            int[] a = A[i], b = B[j];
            int start = Math.max(a[0], b[0]);
            int end = Math.min(a[1], b[1]);
            if (start <= end) {
                res.add(new int[] {start, end});
            }
            if (a[1] == end) i++;
            if (b[1] == end) j++;
        }
        return res.toArray(new int[res.size()][2]);
    }

    @Test
    public void test0() {
        List<int[][]> intervals = new ArrayList<int[][]>();
        intervals.add(new int[][] {{0, 2}, {5, 10}, {13, 23}, {24, 25}});
        intervals.add(new int[][] {{1, 5}, {8, 12}, {15, 24}, {25, 26}});

        int[][] expected = {{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}};
        assertArrayEquals(expected, intervalIntersection(intervals));
    }
}
