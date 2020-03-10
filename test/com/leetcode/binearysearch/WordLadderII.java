package com.leetcode.binearysearch;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.junit.Test;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> path = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<String, List<String>>();
        Set<String> dict = new HashSet<>(wordList);
        buildGraph(beginWord, endWord, graph, dict);
        dfs(beginWord, endWord, graph, path, res);
        return res;
    }

    private void buildGraph(String beginWord, String endWord, Map<String, List<String>> graph,
            Set<String> dict) {
        Set<String> visited = new HashSet<>();
        Set<String> toVisit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        toVisit.add(beginWord);
        boolean isFound = false;

        while (!queue.isEmpty()) {
            visited.addAll(toVisit);
            toVisit.clear();
            int count = queue.size();

            for (int i = 0; i < count; i++) {
                String word = queue.poll();
                List<String> children = getNeighbors(word, dict);
                for (String child : children) {
                    if (child.equals(endWord)) isFound = true;
                    if (!visited.contains(child)) {
                        if (!graph.containsKey(word)) {
                            graph.put(word, new ArrayList<>());
                        }
                        graph.get(word).add(child);
                    }
                    if (!visited.contains(child) && !toVisit.contains(child)) {
                        queue.offer(child);
                        toVisit.add(child);
                    }
                }
                if (isFound) break;
            }

        }
    }

    private List<String> getNeighbors(String str, Set<String> dict) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < str.length(); ++i) {
            char[] arr = str.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ++ch) {
                arr[i] = ch;
                String tmp = String.valueOf(arr);
                if (tmp.equals(str) || !dict.contains(tmp))
                    continue;
                else {
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    private void dfs(String beginWord, String endWord, Map<String, List<String>> graph,
            List<String> path, List<List<String>> res) {
        path.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
        } else if (graph.containsKey(beginWord)) {
            for (String nextWord : graph.get(beginWord)) {
                dfs(nextWord, endWord, graph, path, res);
            }
        }
        path.remove(path.size() - 1);
    }

    @Test
    public void test0() {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        // @formatter:off
        Set<List<String>> expected = new HashSet<>(Arrays.asList(
            Arrays.asList("hit", "hot", "dot", "dog", "cog"),
            Arrays.asList("hit", "hot", "lot", "log", "cog")));
        // @formatter:on
        List<List<String>> actual = findLadders(beginWord, endWord, wordList);
        assertEquals(2, actual.size());
        assertEquals(expected, new HashSet<>(actual));
    }

}
