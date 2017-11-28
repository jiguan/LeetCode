package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class WordLadderII {
    int minLen = Integer.MAX_VALUE;
    List<List<String>> res = new ArrayList<>();
    
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(endWord);
        dfs(endWord, wordList, new ArrayList<>(Arrays.asList(beginWord)));
        Iterator<List<String>> iter = res.iterator();
        while(iter.hasNext()) {
            List<String> list = iter.next();
            if(list.size()>minLen) iter.remove();
        }
        return res;
    }
    
    private void dfs(String endWord, Set<String> wordList, List<String> current) {
        if(current.size()>minLen) return;
        String word = current.get(current.size()-1);
        if(word.equals(endWord)) {
            res.add(new ArrayList<>(current));
            if(current.size()<minLen) minLen = current.size();
            return;
        }
        if(wordList.isEmpty()) {
            return;
        }
        char[] chars = word.toCharArray();
        for(int i =0;i<chars.length;i++) {
            for(char j='a';j<='z';j++) {
                chars[i] = j;
                String cand = new String(chars);
                if(!cand.equals(word)&&wordList.contains(cand)) {
                    current.add(cand);
                    wordList.remove(cand);
                    dfs(endWord, wordList, current);
                    current.remove(cand);
                    wordList.add(cand);
                }
            }
            chars = word.toCharArray();
        }
    }
    
    @Test
    public void test0() {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordList = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"));
        List<List<String>> res = findLadders(beginWord, endWord, wordList);
        assertEquals(2, res.size());
        assertTrue(res.contains(Arrays.asList("hit","hot","dot","dog","cog")));
        assertTrue(res.contains(Arrays.asList("hit","hot","lot","log","cog")));

    }
}
