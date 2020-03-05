package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        boolean[] exists = new boolean[24 * 60];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (String point : timePoints) {
            String[] time = point.split(":");
            int mins = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]) % (24 * 60);
            if (exists[mins]) return 0;
            exists[mins] = true;
            min = Math.min(min, mins);
            max = Math.max(max, mins);
        }
        int res = min + 24 * 60 - max;
        int prev = min;
        for (int i = min + 1; i <= max; ++i) {
            if (exists[i]) {
                res = Math.min(res, i - prev);
                prev = i;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        List<String> timePoints = Arrays.asList("00:00", "23:59");
        int expected = 1;
        assertEquals(expected, findMinDifference(timePoints));
    }

}
