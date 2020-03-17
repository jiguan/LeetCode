package com.leetcode.array.interval;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.leetcode.util.Interval;

/**
 * check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i,
 * which can be called that j is on the "right" of i. For any interval i, you need to store the minimum interval j's
 * index.
 */
public class FindRightInterval {
    public int[] findRightInterval(Interval[] intervals) {
        List<Integer> starts = new ArrayList<>();
        // start - index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < intervals.length; ++i) {
            Interval interval = intervals[i];
            starts.add(interval.start);
            map.put(interval.start, i);
        }

        Collections.sort(starts);
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int end = intervals[i].end;
            int index = binarysearch(starts, end);
            // this is the last interval
            if (index == starts.size()) {
                res[i] = -1;
            } else {
                res[i] = map.get(starts.get(index));
            }
        }
        return res;
    }

    // find the first index larger than / equals val
    private int binarysearch(List<Integer> list, int val) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (list.get(mid) < val) {
                start = mid + 1;
            } else if (list.get(mid) > val) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    public int[] findRightInterval0(Interval[] intervals) {
        int[] result = new int[intervals.length];
        java.util.NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();

        for (int i = 0; i < intervals.length; ++i) {
            intervalMap.put(intervals[i].start, i);
        }

        for (int i = 0; i < intervals.length; ++i) {
            Map.Entry<Integer, Integer> entry = intervalMap.ceilingEntry(intervals[i].end);
            result[i] = (entry != null) ? entry.getValue() : -1;
        }

        return result;
    }

    @Test
    public void test0() {
        Interval[] intervals = {new Interval(3, 4), new Interval(2, 3), new Interval(1, 2)};
        int[] expected = {-1, 0, 1};
        assertArrayEquals(expected, findRightInterval(intervals));
    }

    @Test
    public void test1() {
        Interval[] intervals = {new Interval(13, 14), new Interval(15, 16), new Interval(16, 17)};
        int[] expected = {1, 2, -1};
        assertArrayEquals(expected, findRightInterval(intervals));
    }

    @Test
    public void test2() {
        Interval[] intervals = {new Interval(1, 2)};
        int[] expected = {-1};
        assertArrayEquals(expected, findRightInterval(intervals));
    }
}
