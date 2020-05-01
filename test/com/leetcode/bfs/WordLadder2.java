package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WordLadder2 {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Set<String> visited = new HashSet<>();

        Set<String> wordSet = new HashSet<>();
        for (String str : wordList) {
            wordSet.add(str);
        }
        int steps = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> tmp = new HashSet<>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chars[i];
                        chars[i] = c;
                        String target = String.valueOf(chars);

                        if (endSet.contains(target)) {
                            return steps + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            tmp.add(target);
                            visited.add(target);
                        }
                        chars[i] = old;
                    }
                }
            }
            beginSet = tmp;
            steps++;
        }
        return 0;
    }

    @Test
    public void test0() {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordList = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        assertEquals(5, ladderLength(beginWord, endWord, wordList));
    }
}
