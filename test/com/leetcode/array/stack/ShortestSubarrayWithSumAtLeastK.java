package com.leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length, res = n + 1;
        // record sum to i - 1
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        // index
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n + 1; i++) {
            while (deque.size() > 0 && sum[i] - sum[deque.getFirst()] >= K)
                res = Math.min(res, i - deque.pollFirst());
            // since it is at least k, if current one is less than last one, drop it
            while (deque.size() > 0 && sum[i] <= sum[deque.getLast()])
                deque.pollLast();
            deque.addLast(i);
        }
        return res <= n ? res : -1;
    }
}
