package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;
        wordList.add(endWord);
        List<String> toVisit = new ArrayList<>();
        while (!queue.isEmpty()) {
            String word = queue.poll();
            if (word.equals(endWord)) return count;
            toVisit.addAll(find(word, wordList));
            if (queue.isEmpty()) {
                count++;
                queue.addAll(toVisit);
                toVisit = new ArrayList<>();
            }
        }

        return 0;
    }

    private List<String> find(String word, Set<String> wordList) {
        List<String> toVisit = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < 26; j++) {
                chars[i] = (char) ('a' + j);
                String cand = new String(chars);
                if (!word.equals(cand) && wordList.contains(cand)) {
                    toVisit.add(cand);
                    wordList.remove(cand);
                }
            }
            chars = word.toCharArray(); // recover
        }
        return toVisit;
    }

    @Test
    public void test0() {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordList = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        assertEquals(5, ladderLength(beginWord, endWord, wordList));
    }
}
