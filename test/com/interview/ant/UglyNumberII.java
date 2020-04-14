package com.interview.ant;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int two = 0, three = 0, five = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.min(dp[two] * 2, Math.min(dp[three] * 3, dp[five] * 5));
            if (dp[i] == dp[two]  * 2) {
                two++;
            }
            if (dp[i] == dp[three] * 3) {
                three++;
            }
            if (dp[i] == dp[five] * 5) {
                five++;
            }
        }

        return dp[n - 1];
    }

    @Test
    public void test0() {
        int n = 11;
        assertEquals(15, nthUglyNumber(n));
    }
}
