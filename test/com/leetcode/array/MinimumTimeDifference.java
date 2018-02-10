package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> times = new ArrayList<>();

        for (String time : timePoints) {
            int val = Integer.valueOf(time.substring(0, 2)) * 60 + Integer.valueOf(time.substring(3));
            times.add(val);
        }

        Collections.sort(times);

        int diff = Integer.MAX_VALUE;
        for (int i = 1; i < times.size(); ++i) {
            diff = Math.min(diff, times.get(i) - times.get(i - 1));
        }

        diff = Math.min(diff, times.get(0) + 60 * 24 - times.get(times.size() - 1));

        return diff;
    }

    @Test
    public void test0() {
        List<String> timePoints = Arrays.asList("23:59","00:00");
        int expected = 1;
        assertEquals(expected, findMinDifference(timePoints));
    }
}
