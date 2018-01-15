package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.leetcode.util.Interval;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a.end - b.end);
        int cur = intervals[0].end;

        // non-overlap intervals
        int nonoverlap = 1;
        for (int i = 1; i < intervals.length; ++i) {
            if (cur <= intervals[i].start) {
                nonoverlap++;
                cur = intervals[i].end;
            }
        }
        return intervals.length - nonoverlap;
    }

    @Test
    public void test0() {
        Interval[] intervals = new Interval[] { new Interval(1, 2), new Interval(2, 3), new Interval(3, 4),
                new Interval(1, 3) };
        assertEquals(1, eraseOverlapIntervals(intervals));
    }
}
