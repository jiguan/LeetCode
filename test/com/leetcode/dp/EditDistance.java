package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// http://codercareer.blogspot.com/2011/12/no-25-edit-distance.html
public class EditDistance {
    public int getDistance0(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] distance = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            distance[0][j] = j;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    distance[i + 1][j + 1] = distance[i][j];
                } else {
                    distance[i + 1][j + 1] = Math.min(distance[i][j], Math.min(distance[i][j + 1], distance[i + 1][j])) + 1;
                }
            }
        }

        return distance[m][n];
    }

    public int getDistance(String word1, String word2) {
        int matrix[][] = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < word1.length() + 1; ++i) {
            for (int j = 0; j < word2.length() + 1; ++j) {
                if (i == 0) matrix[i][j] = j;
                else if (j == 0) matrix[i][j] = i;
                else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        matrix[i][j] = matrix[i - 1][j - 1];
                    } else {
                        matrix[i][j] = Math.min(Math.min(matrix[i - 1][j], matrix[i][j - 1]), matrix[i - 1][j - 1]) + 1;
                    }
                }
            }
        }

        return matrix[word1.length()][word2.length()];
    }

    @Test
    public void test0() {
        String s = "Saturday", r = "Sunday";
        assertEquals(3, getDistance(s, r));
    }

    @Test
    public void test1() {
        String s = "", r = "";
        assertEquals(0, getDistance(s, r));
    }

    @Test
    public void test2() {
        String s = "a", r = "a";
        assertEquals(0, getDistance(s, r));
    }

    @Test
    public void test3() {
        String s = "plasma", r = "altruism";
        assertEquals(6, getDistance(s, r));
    }
}
