package com.leetcode.array.slidingwindow;

import static org.junit.Assert.assertEquals;
import java.util.Deque;
import java.util.LinkedList;
import org.junit.Test;

// We cannot use sliding window since there are negative values, check test3()
public class ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        // we need an extra space since sum is for right boundary
        // if not 2, -1, 2 => 2, 1, 3, the diff never exceeds K
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + A[i];
        }

        Deque<Integer> queue = new LinkedList<>();
        int res = A.length + 1;
        for (int i = 0; i < n + 1; ++i) {
            // i = 0 is skipped for both loops
            // i is the length, i = 1, q.first = 0 => sum(A[0])
            while (!queue.isEmpty() && sum[i] - sum[queue.peekFirst()] >= K) {
                res = Math.min(res, i - queue.pollFirst());
            }
            // To keep array sum increasing in the deque.
            // if sum[i-1] > sum[i], and curr - sum(i-1) > k then curr - sum(i) will definitely > k
            // with shorter distance
            while (!queue.isEmpty() && sum[i] <= sum[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.add(i);
        }
        return res > n ? -1 : res;
    }

    @Test
    public void test0() {
        int[] A = {2, -1, 2, 1};
        int K = 3;
        assertEquals(2, shortestSubarray(A, K));
    }

    @Test
    public void test1() {
        int[] A = {84, -37, 32, 40, 95};
        int K = 167;
        // 32 + 40 + 95 = 167
        assertEquals(3, shortestSubarray(A, K));
    }

    @Test
    public void test2() {
        int[] A = {-34, 37, 51, 3, -12, -50, 51, 100, -47, 99, 34, 14, -13, 89, 31, -14, -44, 23,
                -38, 6};
        int K = 151;
        assertEquals(2, shortestSubarray(A, K));
    }

    @Test
    public void test3() {
        int[] A = {5, 1, -5, 5, 10};
        int K = 15;
        assertEquals(2, shortestSubarray(A, K));
    }

    @Test
    public void test4() {
        int[] A = {1};
        int K = 1;
        assertEquals(1, shortestSubarray(A, K));
    }

    @Test
    public void test5() {
        int[] A = {2, -1, 2};
        int K = 3;
        assertEquals(3, shortestSubarray(A, K));
    }
}
