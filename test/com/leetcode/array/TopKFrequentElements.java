package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }
        // num - times
        Map<Integer, Integer> times = new HashMap<>();
        for (int num : nums) {
            times.put(num, times.getOrDefault(num, 0) + 1);
        }
        // 1 element, 1 times
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : times.keySet()) {
            int time = times.get(key);
            if (buckets[time] == null) {
                buckets[time] = new ArrayList<>();
            }
            buckets[time].add(key);
        }

        for (int i = buckets.length - 1; i >= 0 && res.size() < k; --i) {
            if (buckets[i] != null) {
                for (int j = 0; j < buckets[i].size() && res.size() < k; ++j) {
                    res.add(buckets[i].get(j));
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {1};
        int k = 1;
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, topKFrequent(nums, k));
    }
}
