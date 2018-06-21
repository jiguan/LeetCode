package com.hackerank.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MostFrequentlyUsedWord {
    List<String> retrieveMostFrequentlyUsedWords(String literatureText, List<String> wordsToExclude) {
        // WRITE YOUR CODE HERE
        List<String> res = new LinkedList<>();
        if (literatureText == null || literatureText.isEmpty()) return res;
        Set<String> exclude = new HashSet<>(wordsToExclude.size());
        for (String word : wordsToExclude) {
            exclude.add(word.toLowerCase());
        }
        int max = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String word : literatureText.toLowerCase().split("\\s+")) {
            if (exclude.contains(word)) continue;
            int times = map.getOrDefault(word, 0) + 1;
            map.put(word, times);
            max = Math.max(max, times);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                res.add(entry.getKey());
            }
        }

        return res;
    }

    @Test
    public void test0() {
        String literatureText = "remeo  remeo remeo";
        List<String> wordsToExclude = Arrays.asList("");
        assertEquals(Arrays.asList("remeo"), retrieveMostFrequentlyUsedWords(literatureText, wordsToExclude));
    }
}
