package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class WordPermutation {
    public List<String> permutate(String word) {
        List<String> res = new ArrayList<>();
        helper(word.toCharArray(), 0, res);
        return res;
    }

    private void helper(char[] word, int index, List<String> res) {
        if (index == word.length) {
            res.add(String.valueOf(word));
            return;
        }

        Set<Character> set = new HashSet<>();
        for (int i = index; i < word.length; ++i) {
            if (set.add(word[i]) == false) continue;
            swap(word, index, i);
            helper(word, index + 1, res);
            swap(word, index, i);
        }
    }

    private void swap(char[] word, int i, int j) {
        char tmp = word[i];
        word[i] = word[j];
        word[j] = tmp;
    }

    @Test
    public void test0() {
        String word = "abc";
        List<String> res = permutate(word);
        assertEquals(6, new HashSet<>(res).size());
    }

}
