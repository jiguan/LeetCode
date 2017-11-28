package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class MaximumProductofWordLengths {
    public int maxProduct0(String[] words) {
        if (words == null || words.length == 0)
            return 0;
        int len = words.length;
        int[] value = new int[len];
        for (int i = 0; i < len; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                value[i] |= 1 << (tmp.charAt(j) - 'a');
            }
        }
        
        int maxProduct = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct))
                    maxProduct = words[i].length() * words[j].length();
            }
        }
        return maxProduct;
    }

    public int maxProductWithSet(String[] words) {
        int length = words.length;
        List<Set<Character>> map = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String word = words[i];
            Set<Character> set = new HashSet<>(26);
            for (char c : word.toCharArray()) {
                set.add(new Character(c));
            }
            map.add(set);
        }
        int max = 0;
        for (Set<Character> s : map) {
            for (Set<Character> another : map) {
                Set<Character> firstSize = new HashSet<>(s);
                int size = firstSize.size() * another.size();
                if (size > max) {
                    if (!firstSize.removeAll(another)) {
                        max = size;
                    }
                }
            }
        }
        return max;
    }

    public int maxProductWithArray(String[] words) {
        int length = words.length;
        boolean[][] map = new boolean[length][26];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                map[i][c - 'a'] = true;
            }
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            boolean[] word1 = map[i];
            mark: for (int j = i + 1; j < length; j++) {
                boolean[] word2 = map[j];
                if (max > words[i].length() * words[j].length()) {
                    continue;
                }
                for (int k = 0; k < 26; k++) {
                    if (word1[k] && word2[k]) {
                        continue mark;
                    }
                }
                max = words[i].length() * words[j].length();
            }
        }
        return max;
    }

    public int maxProduct(String[] words) {
        int length = words.length;
        int[] value = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                value[i] |= 1 << (c - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int product = words[i].length() * words[j].length();
                if ((value[i] & value[j]) == 0 && product > max) {
                    max = product;
                }
            }
        }
        return max;
    }

    @Test
    public void test0() {
        String[] words = new String[] {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        assertEquals(16, maxProduct(words));
    }

    @Test
    public void test2() {
        String[] words = new String[] {"abc", "a", "b", ""};
        assertEquals(1, maxProduct(words));
    }

    @Test
    public void test1() {
        String word = "abcdefg";
        int value = 0;
        for (int i = 0; i < word.length(); i++) {
            int a = (word.charAt(i) - 'a');
            int b = 1 << a;
            value |= b;
        }
        System.out.println(value);
    }
}
