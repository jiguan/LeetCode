package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class RepeatedDnaSequences {
    public List<String> findRepeatedDnaSequences0(String s) {
        Set<String> set = new HashSet<>();
        Set<String> reset = new HashSet<>();
        List<String> result = new ArrayList<>();
        int len = s.length();
        if (len <= 10) return result;
        for (int i = 0; i <= len - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (!set.add(sub)) {
                reset.add(sub);
            }
        }
        result.addAll(reset);
        return result;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> added = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String sub = s.substring(i, i + 10);
            if (!visited.add(sub) && added.add(sub)) {
                res.add(sub);
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String s = "AAAAAAAAAAA";
        assertEquals(Arrays.asList("AAAAAAAAAA"), findRepeatedDnaSequences(s));
    }
}
