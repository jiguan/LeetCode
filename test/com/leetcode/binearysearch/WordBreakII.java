package com.leetcode.binearysearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        return dfs(s, new HashSet<String>(wordDict), map);
    }

    private List<String> dfs(String s, Set<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> res = new LinkedList<>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String nextString = s.substring(word.length());
                if (nextString.isEmpty()) {
                    res.add(word);
                } else {
                    List<String> matches = dfs(nextString, wordDict, map);
                    for (String match : matches) {
                        res.add(word + " " + match);
                    }
                }
            }
        }
        map.put(s, res);
        return res;
    }

    @Test
    public void test0() {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        Set<String> actual = new HashSet<>(wordBreak(s, wordDict));
        assertEquals(2, actual.size());
        assertTrue(actual.contains("cats and dog"));
        assertTrue(actual.contains("cat sand dog"));
    }
}
