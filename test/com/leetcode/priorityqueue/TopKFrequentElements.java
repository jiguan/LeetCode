package com.leetcode.priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        if(nums.length==0||k==0) return result;  
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.get(num)==null ? 1 : map.get(num) +1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        queue.addAll(map.entrySet());
        for(int i=0;i<k;i++) {
            result.add(queue.poll().getKey());
        }
        return result;
    }
}   
