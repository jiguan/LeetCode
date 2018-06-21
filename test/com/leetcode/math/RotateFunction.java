package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RotateFunction {
  //@formatter:off
  //
  // F(k)   = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]
  // F(k-1) = 0 * Bk-1[0] + 1 * Bk-1[1] + ... + (n-1) * Bk-1[n-1]
  //        = 0 * Bk[1] + 1 * Bk[2] + ... + (n-2) * Bk[n-1] + (n-1) * Bk[0]
  //  
  // F(k) - F(k-1) = (1-n) * Bk[0] + Bk[1] + Bk[2] + ... + Bk[n-1] 
  //               = sum - n * Bk[0]
  //@formatter:on
    public int maxRotateFunction(int[] A) {
        int F = 0, sum = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            F += i * A[i]; // calculate F(0)
            sum += A[i];
        }

        int max = F;
        // B0[0] = A[0]; B1[0] = A[n-1]; B2[0] = A[n-2]
        for (int i = 1; i < n; i++) {
            F = F + sum - n * A[n - i];
            max = Math.max(max, F);
        }
        return max;
    }

    @Test
    public void test0() {
        int[] A = new int[] { 4, 3, 2, 6 };
        assertEquals(26, maxRotateFunction(A));
    }
}
