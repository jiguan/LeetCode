package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split("\\s+");
        Map<String, Integer> times = new HashMap<>();
        for (String word : words) {
            if (!ban.contains(word)) {
                times.put(word, times.getOrDefault(word, 0) + 1);
            }
        }
        return Collections.max(times.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Test
    public void test0() {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        String expected = "ball";
        assertEquals(expected, mostCommonWord(paragraph, banned));
    }
}
