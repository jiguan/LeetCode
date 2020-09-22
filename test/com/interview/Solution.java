package com.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.Test;

public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq =
                new PriorityQueue<>((n1, n2) -> (n2.getValue() - n1.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k)
                pq.poll();
        }

        int[] res = new int[k];
        for (int i = 0; i < res.length; ++i) {
            res[i] = pq.poll().getKey();
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        
        topKFrequent(nums, k);
    }
}
