package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Test;

import com.leetcode.util.Interval;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the
 * minimum number of conference rooms required.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();
        endTimes.offer(intervals[0].end);
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start >= endTimes.peek()) {
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }

        return endTimes.size();
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
