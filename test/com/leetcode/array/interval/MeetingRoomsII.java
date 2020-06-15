package com.leetcode.array.interval;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.leetcode.util.Interval;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the
 * minimum number of conference rooms required.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class MeetingRoomsII {
    // greedy
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        int len = intervals.length;
        int[] start = new int[len];
        int[] end = new int[len];
        for (int i = 0; i < len; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int endIndex = 0, res = 0;
        for (int i = 0; i < len; ++i) {
            if (start[i] < end[endIndex]) res++;
            else {
                // current start is later than last end time
                endIndex++;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(0, 30);
        intervals[1] = new Interval(5, 10);
        intervals[2] = new Interval(15, 20);
        assertEquals(2, minMeetingRooms(intervals));
    }

    @Test
    public void test1() {
        Interval[] intervals = new Interval[2];
        intervals[0] = new Interval(0, 10);
        intervals[1] = new Interval(15, 20);
        assertEquals(1, minMeetingRooms(intervals));
    }

    @Test
    public void test2() {
        Interval[] intervals = new Interval[2];
        intervals[0] = new Interval(0, 15);
        intervals[1] = new Interval(10, 20);
        assertEquals(2, minMeetingRooms(intervals));
    }
}
