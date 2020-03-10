package com.interview.phone.ant;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] pointers = new int[primes.length];
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; ++i) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                // move the pointer ahead since we don't know current min dp[i] until the whole loop
                // is finish
                if (dp[i - 1] == primes[j] * dp[pointers[j]]) {
                    pointers[j]++;
                }
                // 2 * dp[index2]
                dp[i] = Math.min(dp[i], primes[j] * dp[pointers[j]]);
            }

        }

        return dp[n - 1];
    }

    @Test
    public void test0() {
        int n = 12;
        int[] primes = {2, 7, 13, 19};
        // [1,2,4,7,8,13,14,16,19,26,28,32]
        assertEquals(32, nthSuperUglyNumber(n, primes));
    }
}
