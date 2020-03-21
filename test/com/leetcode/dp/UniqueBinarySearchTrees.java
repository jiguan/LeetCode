package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// https://www.youtube.com/watch?v=YDf982Lb84o&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=37
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        // Empty tree
        dp[0] = 1;
        // one node
        dp[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                // there is (i - 1) children nodes
                dp[i] += dp[i - 1 - j] * dp[j];
            }
        }
        return dp[n];
    }

    @Test
    public void test0() {
        int n = 3;
        assertEquals(Integer.valueOf(5), Integer.valueOf(numTrees(n)));
    }
}
