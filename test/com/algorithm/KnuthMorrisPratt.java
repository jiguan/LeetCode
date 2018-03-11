package com.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

// Time complexity O(m+n), O(m) to visit the search string and O(n) to build the table
// https://www.youtube.com/watch?v=GTJr8OvyEVQ
// If there is a mismatch, prior to the mismatch character, is there a substring that is prefix and suffix at the same time? e.g. a b * * a b
// if so, then starts with next character after the prefix, since the suffix is same with the prefix, which means in S, the substring prior to the mismatch character is same with the one in T
// e.g: in this case, compare y and c next time
// a b c x [a b] y
// [a b] c x a b z 
// If not, then starts from the beginning of the target
// The benefit is in S, we don't need to set the pointer back
// The pointer move back and forth in T only
public class KnuthMorrisPratt {
    public List<Integer> kmp_search(String search, String target) {
        int[] lsp = computeLspTable(target);
        List<Integer> res = new LinkedList<>();

        int j = 0; // Number of chars matched in pattern
        for (int i = 0; i < search.length(); i++) {
            while (j > 0 && search.charAt(i) != target.charAt(j)) {
                // Fall back in the pattern
                // Go to previous one (last matched) and find out the next index
                j = lsp[j - 1];
            }
            if (search.charAt(i) == target.charAt(j)) {
                j++;
                if (j == target.length()) {
                    res.add(i + 1 - j);
                    j = 0;
                }
            }
        }
        return res;
    }

    // longest suffix-prefix
    // As a whole, storing the next index where we should start searching
    // If there is a mismatch, then go to previous one (j-1) since it is the last matched
    // Get its should-start index and compare with current char
    public int[] computeLspTable(String pattern) {
        int[] lsp = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            // Start by assuming we're extending the previous LSP
            int j = lsp[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lsp[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) j++;
            lsp[i] = j;
        }
        return lsp;
    }

    @Test
    public void test0() {
        String S = "ABC ABCDAB ABCDABCDABDE";
        String W = "ABCDABD";
        assertEquals(Arrays.asList(15), kmp_search(S, W));
    }

    @Test
    public void test1() {
        String W = "AABAABAAA";
        int[] table = new int[]{0, 1, 0, 1, 2, 3, 4, 5, 2};
        com.leetcode.util.Arrays.assertEquals(table, computeLspTable(W));
    }
}
