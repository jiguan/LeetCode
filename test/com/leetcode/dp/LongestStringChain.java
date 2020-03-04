package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class LongestStringChain {
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) return 0;
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        // predecessor - chain length
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {
            int count = 0;
            for (int i = 0; i < word.length(); ++i) {
                String sub = word.substring(0, i) + word.substring(i + 1);
                count = Math.max(count, map.getOrDefault(sub, 0) + 1);
            }
            map.put(word, count);
            res = Math.max(res, count);
        }
        return res;
    }

    @Test
    public void test0() {
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        int expected = 4;
        assertEquals(expected, longestStrChain(words));
    }
}
