package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class MinimumCostToCutAStick {
    public int minCost(int n, int[] cuts) {
        List<Integer> c = new ArrayList<>();
        for (int cut : cuts) {
            c.add(cut);
        }
        c.addAll(Arrays.asList(0, n));
        Collections.sort(c);
        int[][] dp = new int[c.size()][c.size()];
        for (int i = c.size() - 1; i >= 0; --i)
            for (int j = i + 1; j < c.size(); ++j) {
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j],
                            c.get(j) - c.get(i) + dp[i][k] + dp[k][j]);
            }
        return dp[0][c.size() - 1];
    }

    @Test
    public void test0() {
        int n = 7;
        int[] cuts = {1, 3, 4, 5};
        assertEquals(16, minCost(n, cuts));
    }
}
