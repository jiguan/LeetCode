package com.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // num - times
        Map<Integer, Integer> times = new HashMap<>();
        for (int num : nums) {
            times.put(num, times.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : times.keySet()) {
            int time = times.get(key);
            if (bucket[time] == null) {
                bucket[time] = new ArrayList<>();
            }
            bucket[time].add(key);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; --i) {
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size() && res.size() < k; ++j) {
                    res.add(bucket[i].get(j));
                }
            }
        }

        return res;
    }
}
