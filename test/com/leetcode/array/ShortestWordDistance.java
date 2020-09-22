package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance (index
 * difference) between these two words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. Given word1
 * = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.
 */
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int m = -1;
        int n = -1;

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (word1.equals(s)) {
                m = i;
                if (n != -1)
                    min = Math.min(min, m - n);
            } else if (word2.equals(s)) {
                n = i;
                if (m != -1)
                    min = Math.min(min, n - m);
            }
        }
        return min;
    }

    // Follow up: now you are given the list of words and your method will be called
    // repeatedly many times with different parameters. How would you optimize it?
    // word, index
    Map<String, List<Integer>> map = new HashMap<>();

    public void wordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = map.getOrDefault(words[i], new ArrayList<>());
            list.add(i);
            map.put(words[i], list);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);

        int result = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        while (i < l1.size() && j < l2.size()) {
            result = Math.min(result, Math.abs(l1.get(i) - l2.get(j)));
            if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // Follow up: now word1 could be the same as word2.
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int prev = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            if (words[i] == word1 || words[i] == word2) {
                if (prev != -1 && (word1 == word2 || words[i] != words[prev])) {
                    res = Math.min(res, i - prev);
                }
                prev = i;
            }
        }
        return res;
    }
    
    @Test
    public void test0() {
        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding", word2 = "practice";
        assertEquals(3, shortestDistance(words, word1, word2));
    }
}
