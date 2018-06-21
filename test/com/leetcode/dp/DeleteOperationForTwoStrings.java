package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        // find the most common string
        int len1 = word1.length(), len2 = word2.length();
        int[][] matrix = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                char c1 = word1.charAt(i), c2 = word2.charAt(j);
                if (c1 == c2) {
                    matrix[i + 1][j + 1] = matrix[i][j] + 1;
                } else {
                    matrix[i + 1][j + 1] = Math.max(matrix[i + 1][j], matrix[i][j + 1]);
                }
            }
        }

        // longest common string
        int lcs = matrix[len1][len2];
        return len1 - lcs + len2 - lcs;
    }

    @Test
    public void test0() {
        String word1 = "sea", word2 = "tea";
        assertEquals(Integer.valueOf(2), Integer.valueOf(minDistance(word1, word2)));
    }
}
