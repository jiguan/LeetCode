package com.interview.indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {
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
                    List<String> list = dfs(next, wordDict, cache);
                    for(String match : list) {
                        res.add(word + " " + match);
                    }
                }
            }
        }
        cache.put(s, res);
        return res;
    }
}
