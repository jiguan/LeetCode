package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

// Zillow
public class SubsetPermutation {
    // input: ab, return [a, b, ab, ba]
    public List<String> permutate(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.isEmpty()) return res;
        List<Character> array = new ArrayList<>();
        for (char c : str.toCharArray()) {
            array.add(c);
        }
        backtrack(array, new StringBuilder(), res);
        return res;
    }

    private void backtrack(List<Character> str, StringBuilder curr, List<String> res) {
        if (curr.length() != 0) res.add(curr.toString());
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < str.size(); ++i) {
            if (visited.add(str.get(i)) == false) continue;
            char c = str.remove(i);
            curr.append(c);
            backtrack(str, curr, res);
            curr.setLength(curr.length() - 1);
            str.add(i, c);
        }
    }

    @Test
    public void test0() {
        String str = "ab";
        Set<String> expected = new HashSet<>(Arrays.asList("ab", "ba", "a", "b"));
        assertEquals(expected, new HashSet<>(permutate(str)));
    }

    @Test
    public void test1() {
        String str = "aaa";
        Set<String> expected = new HashSet<>(Arrays.asList("aaa", "aa", "a"));
        assertEquals(expected, new HashSet<>(permutate(str)));
    }

    @Test
    public void test2() {
        String str = "aab";
        Set<String> expected = new HashSet<>(Arrays.asList("aab", "aba", "baa", "ba", "ab", "aa", "a", "b"));
        assertEquals(expected, new HashSet<>(permutate(str)));
    }
}
