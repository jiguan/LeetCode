package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LongestWordInDictionaryThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        // length is the highest priority
        // Collections.sort(d, (a, b) -> a.length() == b.length() ?
        // a.compareTo(b) : b.length() - a.length());
        String res = "";
        for (String word : d) {
            int index = 0;
            for (int i = 0; i < s.length() && index < word.length(); ++i) {
                char c = s.charAt(i);
                if (c == word.charAt(index)) {
                    index++;
                }
            }
            if (index == word.length() && res.length() <= word.length()) {
                if (word.length() > res.length() || word.compareTo(res) < 0) res = word;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String s = "abpcplea";
        List<String> d = Arrays.asList("ale", "apple", "monkey", "plea");
        String expected = "apple";

        assertEquals(expected, findLongestWord(s, d));
    }

    @Test
    public void test1() {
        String s = "aaa";
        List<String> d = Arrays.asList("aaa", "aa", "a");
        String expected = "aaa";

        assertEquals(expected, findLongestWord(s, d));
    }
}
