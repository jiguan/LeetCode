package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.Test;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // 1 element, 1 times
        List<String>[] times = new List[words.length + 1];

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            if (times[freq] == null) {
                times[freq] = new LinkedList<>();
            }
            times[freq].add(entry.getKey());
        }

        List<String> res = new LinkedList<>();
        for (int i = times.length - 1; i >= 0 && res.size() < k; --i) {
            if (times[i] != null) {
                Collections.sort(times[i]);
                while (res.size() < k && !times[i].isEmpty()) {
                    res.add(times[i].remove(0));
                }
            }
        }

        return res;
    }

    public List<String> topKFrequent1(String[] words, int k) {
        List<String> res = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // increasing order
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey())
                        : a.getValue() - b.getValue());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            // remove the least
            if (pq.size() > k) pq.poll();

        }

        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }

        return res;
    }


    @Test
    public void test0() {
        String[] words = new String[] {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> expected = Arrays.asList("i", "love");
        assertEquals(expected, topKFrequent1(words, k));
    }
    
    @Test
    public void test1() {
        String[] words = new String[] {"i"};
        int k = 1;
        List<String> expected = Arrays.asList("i");
        assertEquals(expected, topKFrequent(words, k));
    }
}
