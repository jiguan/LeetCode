package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Test;

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        // char - times
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        pq.addAll(map.entrySet());
        StringBuffer buffer = new StringBuffer();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            int times = entry.getValue();
            for (int i = 0; i < times; ++i) {
                buffer.append(entry.getKey());
            }
        }

        return buffer.toString();
    }

    @Test
    public void test0() {
        String input = "trreee";
        String expect = "eeerrt";
        assertEquals(expect, frequencySort(input));
    }

}
