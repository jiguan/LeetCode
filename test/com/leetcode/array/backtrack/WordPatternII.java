package com.leetcode.array.backtrack;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return traverse(str, 0, pattern, 0, map, set);
    }

    private boolean traverse(String str, int i, String pattern, int j, Map<Character, String> map,
            Set<String> set) {
        if (i == str.length() && j == pattern.length()) return true;
        if (i == str.length() || j == pattern.length()) return false;

        // get current pattern character
        char p = pattern.charAt(j);

        // if the pattern character exists
        if (map.containsKey(p)) {
            String word = map.get(p);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(word, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return traverse(str, i + word.length(), pattern, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int end = i + 1; end <= str.length(); end++) {
            String word = str.substring(i, end);

            if (set.contains(word)) {
                continue;
            }

            // create or update it
            map.put(p, word);
            set.add(word);

            // continue to match the rest
            if (traverse(str, end, pattern, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(p);
            set.remove(word);
        }

        return false;
    }

    @Test
    public void test0() {
        String pattern = "abab";
        String str = "redblueredblue";
        assertTrue(wordPatternMatch(pattern, str));
    }
}
