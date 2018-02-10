package com.leetcode.backtracking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BeautifulArrangement {
    int count = 0;

    public int countArrangement(int N) {
        helper(N, 1, new boolean[N + 1]);
        return count;
    }

    private void helper(int N, int num, boolean[] visited) {
        if (num > N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && (num % (i+1) == 0 || (i+1) % num == 0)) {
                visited[i] = true;
                helper(N, num + 1, visited);
                visited[i] = false;
            }
        }
    }

    @Test
    public void test0() {
        assertEquals(2, countArrangement(2));
    }
}
