package com.leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your
 * method will be called repeatedly many times with different parameters. How would you optimize it?
 * 
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
 * and word2 and return the shortest distance between these two words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “coding”, word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.
 * 
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistanceII {

}

class WordDistance {
    Map<String, List<Integer>> map;

    public WordDistance(String[] words) {
        map = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<>());
            }
            map.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        int i = 0, j = 0, res = Integer.MIN_VALUE;
        while (i < map.get(word1).size() && j < map.get(word2).size()) {
            int index1 = map.get(word1).get(i), index2 = map.get(word2).get(j);
            res = Math.min(res, Math.abs(index1 - index2));
            if (index1 < index2) {
                ++i;
            } else {
                ++j;
            }
        }
        return res;
    }
}