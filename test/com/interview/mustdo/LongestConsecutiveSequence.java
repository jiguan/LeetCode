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
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) continue;
            int left = map.getOrDefault(nums[i] - 1, 0);
            int right = map.getOrDefault(nums[i] + 1, 0);
            int sum = left + right + 1;
            res = Math.max(res, sum);
            // cannot ignore, since for duplicated number, we want to continue
            map.put(nums[i], sum);
            map.put(nums[i] - left, sum);
            map.put(nums[i] + right, sum);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {4, 0, -4, -2, 2, 5, 2, 0, -8, -1, 7, 4, 5, -4, 6,  -3};
        assertEquals(5, longestConsecutive(nums));
    }
}
