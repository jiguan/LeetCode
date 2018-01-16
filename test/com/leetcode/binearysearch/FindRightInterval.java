package com.leetcode.binearysearch;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.Arrays;
import com.leetcode.util.Interval;

public class FindRightInterval {
    public int[] findRightInterval(Interval[] intervals) {
        // Start - Index
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> starts = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
            starts.add(intervals[i].start);
        }
        Collections.sort(starts);
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int end = intervals[i].end;
            int start = binarySearch(end, starts);
            if (start < end) {
                res[i] = -1;
            } else {
                res[i] = map.get(start);
            }
        }
        return res;
    }

    // Find the first element larger or equals end
    public int binarySearch(int val, List<Integer> list) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return list.get(left);
    }

    @Test
    public void test0() {
        Interval[] intervals = Interval.build(" [ [1,4], [2,3], [3,4] ]");
        int[] expected = new int[]{-1, 2, -1};
        assertTrue(Arrays.equals(expected, findRightInterval(intervals)));
    }
}
