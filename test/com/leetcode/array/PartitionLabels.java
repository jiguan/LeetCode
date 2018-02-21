package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/*
 * One letter can only occur in one partition
 * As many as partition as possible
 */

public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        int[] lastSeen = new int[26];
        for (int i = 0; i < S.length(); ++i) {
            lastSeen[S.charAt(i) - 'a'] = i;
        }

        int start = 0, end = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            end = Math.max(end, lastSeen[c - 'a']);
            if (end == i) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String S = "ababcbacadefegdehijhklij";
        List<Integer> expected = Arrays.asList(9, 7, 8);
        assertEquals(expected, partitionLabels(S));
    }
}
