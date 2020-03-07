package com.leetcode.interval;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        int index = 0;
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            res.add(intervals[index++]);
        }

        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[index][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[index][1], newInterval[1]);
            index++;
        }
        res.add(newInterval);

        while (index < intervals.length) {
            res.add(intervals[index++]);
        }
        int[][] arr = new int[res.size()][2];
        arr = res.toArray(arr);
        return arr;

    }

    @Test
    public void test0() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        int[][] expected = {{1, 2}, {3, 10}, {12, 16}};
        assertArrayEquals(expected, insert(intervals, newInterval));
    }
}
