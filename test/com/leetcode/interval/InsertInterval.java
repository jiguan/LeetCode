package com.leetcode.interval;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.Interval;

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new LinkedList<>();
        int index = 0;
        while (index < intervals.size() && intervals.get(index).end < newInterval.start) {
            res.add(intervals.get(index++));
        }
        while (index < intervals.size() && intervals.get(index).start <= newInterval.end) {
            newInterval.start = Math.min(intervals.get(index).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(index).end, newInterval.end);
            index++;
        }
        res.add(newInterval);
        while (index < intervals.size()) {
            res.add(intervals.get(index++));
        }
        return res;
    }

    @Test
    public void test0() {
        List<Interval> intervals = Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(12, 16));
        Interval newInterval = new Interval(4, 8);
        List<Interval> res = insert(intervals, newInterval);
        List<Interval> expected = Arrays.asList(new Interval(1, 2), new Interval(3, 10), new Interval(12, 16));
        assertEquals(expected, res);
    }
}
