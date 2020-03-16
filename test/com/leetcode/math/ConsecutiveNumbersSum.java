package com.leetcode.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ConsecutiveNumbersSum {
    /*
     * N = (x+1) + (x+2) + ... + (x+k)
     * 
     * N = kx + k(k+1)/2 N * 2 = k(2x + k + 1),where x >= 0, k >= 1
     * 
     * Either k or 2x + k + 1 is odd.
     */
    public int consecutiveNumbersSum(int N) {
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

    @Test
    public void test0() {
        int N = 15;
        assertEquals(4, consecutiveNumbersSum(N));
    }

}
