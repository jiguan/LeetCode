package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FriendCircles {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int count = 0;
        int[] visited = new int[M.length];
        for (int i = 0; i < M.length; ++i) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; ++j) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    @Test
    public void test0() {
        int[][] M = new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        assertEquals(2, findCircleNum(M));
    }
}
