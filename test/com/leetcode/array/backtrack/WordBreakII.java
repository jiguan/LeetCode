package com.leetcode.array.backtrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
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
        return dfs(s, wordDict, new HashMap<String, List<String>>());
    }
    
    private List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> cache) {
        if(cache.containsKey(s)) {
            return cache.get(s);
        }
        
        List<String> res = new ArrayList<>();
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                String next = s.substring(word.length());
                if(next.isEmpty()) {
                    res.add(word);
                } else {
                    List<String> matches = dfs(next, wordDict, cache);
                    for(String match : matches) {
                        res.add(word + " " + match);
                    }
                }
            }
        }
        cache.put(s, res);
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
    
    @Test
    public void test1() {
        String s = "12345678";
        List<String> wordDict = Arrays.asList("123", "456");
        Set<String> actual = new HashSet<>(wordBreak(s, wordDict));
        assertEquals(2, actual.size());
        assertTrue(actual.contains("cats and dog"));
        assertTrue(actual.contains("cat sand dog"));
    }
}
