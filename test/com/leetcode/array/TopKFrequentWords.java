package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String>[] times = new List[words.length];

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            if (times[freq] == null) {
                times[freq] = new ArrayList<>();
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

    @Test
    public void test0() {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> expected = Arrays.asList("i", "love");
        assertEquals(expected, topKFrequent(words, k));
    }
}
