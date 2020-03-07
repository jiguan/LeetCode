package com.interview.mustdo;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        // num, length
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) continue;
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int sum = left + right + 1;
            res = Math.max(res, sum);
            // we don't need to iterate all the way to left/right since we ignore visited element
            // the head and tail are the most important
            map.put(num, sum); // record to ignore it next time
            map.put(num - left, sum);
            map.put(num + right, sum);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {4, 0, -4, -2, 2, 5, 2, 0, -8, -1, 7, 4, 5, -4, 6, -3};
        assertEquals(5, longestConsecutive(nums));
    }
}
