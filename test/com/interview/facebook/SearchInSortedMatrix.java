package com.interview.facebook;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.Test;

//
// find the Kth smallest element in a row-wise and column-wise sorted 2D array
// 10, 20, 30, 40
// 15, 25, 35, 45
// 24, 29, 37, 48
// 32, 33, 39, 50
//
// k = 1 => 10 (1st smallest)
// k = 6 => 29 (6-th smallest)
//

public class SearchInSortedMatrix {
    public int findKsmallest(int[][] matrix, int K) {
        int m = matrix.length, n = matrix[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int v1 = matrix[a[0]][a[1]];
            int v2 = matrix[b[0]][b[1]];
            return Integer.compare(v1, v2);
        });

        pq.offer(new int[] {0, 0});

        int res = -1;
        int index = 0;
        boolean[][] visited = new boolean[n][n];
        while (index < K) {
            int[] point = pq.poll();
            int i = point[0], j = point[1];
            if (visited[i][j]) continue;
            res = matrix[i][j];
            visited[i][j] = true;
            index++;
            // add elements on right and below
            if (i + 1 < m) {
                pq.offer(new int[] {i + 1, j});
            }
            if (j + 1 < n) {
                pq.offer(new int[] {i, j + 1});
            }
        }
        return res;
    }

    public int findKsmallestLeftToRight(int[][] matrix, int K) {
        int m = matrix.length, n = matrix[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int v1 = matrix[a[0]][a[1]];
            int v2 = matrix[b[0]][b[1]];
            return Integer.compare(v1, v2);
        });

        int res = -1;
        int index = 0;
        for (int i = 0; i < m; ++i) {
            pq.offer(new int[] {i, 0});
        }

        // k * log (n)
        while (index < K) {
            int[] point = pq.poll();
            int i = point[0], j = point[1];
            res = matrix[i][j];
            index++;

            if (j + 1 < n) {
                pq.offer(new int[] {i, j + 1});
            }
        }
        return res;
    }

    @Test
    public void test0() {
        // @formatter:off
        int[][] matrix = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {24, 29, 37, 48},
            {32, 33, 39, 50}
        };
        // @formatter:on
        int K = 6;
        assertEquals(29, findKsmallest(matrix, K));
    }

    @Test
    public void testHashSet() {
        int[] p1 = new int[] {0, 1};
        int[] p2 = new int[] {0, 1};
        Set<int[]> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        assertEquals(2, set.size());
    }

}
