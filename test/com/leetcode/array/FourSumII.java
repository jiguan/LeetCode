package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < B.length; ++j) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < C.length; ++i) {
            for (int j = 0; j < D.length; ++j) {
                int sum = C[i] + D[j];
                // need to add existing times since they could combine
                // differently
                res += map.getOrDefault(-sum, 0);
            }
        }

        return res;
    }

    @Test
    public void test0() {
        int[] A = new int[]{1, 2}, B = new int[]{-2, -1}, C = new int[]{-1, 2}, D = new int[]{0, 2};
        assertEquals(2, fourSumCount(A, B, C, D));
    }

    @Test
    public void test1() {
        int[] A = new int[]{-1, -1}, B = new int[]{-1, 1}, C = new int[]{-1, 1}, D = new int[]{1, -1};
        assertEquals(6, fourSumCount(A, B, C, D));
    }
}
