package com.leetcode.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// how many ways can we write it as a sum of consecutive positive integers
public class ConsecutiveNumbersSum {
    /*
     * N = (x+1) + (x+2) + ... + (x+k)
     * 
     * N = kx + k(k+1)/2 N * 2 = k(2x + k + 1),where x >= 0, k >= 1
     * 
     * Either k or 2x + k + 1 is odd.
     */
    public int consecutiveNumbersSumIter(int N) {
        int res = 1, count = 0;
        while (N % 2 == 0)
            N /= 2;
        for (int i = 3; i * i <= N; i = i + 2) {
            count = 0;
            while (N % i == 0) {
                N /= i;
                count++;
            }
            res = res * (count + 1);
        }
        return N == 1 ? res : res * 2;
    }

    // N = k, k + 1, k + 2, ..., k + (i - 1)
    // ki = N - (i - 1) * i / 2

    public int consecutiveNumbersSum(int N) {
        int ans = 0;
        for (int i = 1; i * (i - 1) / 2 < N; ++i)
            if ((N - i * (i - 1) / 2) % i == 0) ++ans;
        return ans;
    }

    @Test
    public void test0() {
        int N = 15;
        assertEquals(4, consecutiveNumbersSum(N));
    }

    @Test
    public void test1() {
        int N = 5;
        assertEquals(2, consecutiveNumbersSum(N));
    }

}
