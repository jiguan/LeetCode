package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int res = 1;

        while (!visited.contains(endWord)) {
            Set<String> nextLevel = new HashSet<>();
            for (String word : visited) {
                for (int i = 0; i < word.length(); ++i) {
                    char[] chs = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        chs[i] = ch;
                        String tmp = new String(chs);
                        if (dict.contains(tmp)) {
                            nextLevel.add(tmp);
                            dict.remove(tmp);
                        }
                    }
                }
            }
            if (nextLevel.isEmpty()) return 0;
            res++;
            visited = nextLevel;
        }
        return res;
    }

    @Test
    public void test0() {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordList = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        assertEquals(5, ladderLength(beginWord, endWord, wordList));
    }
}
