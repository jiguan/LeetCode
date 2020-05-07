package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class MinimumKnightMoves {
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Map<String, Integer> dp = new HashMap<>();
        int res = helper(x, y, dp);
        return res;
    }

    private int helper(int x, int y, Map<String, Integer> dp) {
        String key = x + "," + y;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        if (x + y == 0) {
            return 0;
        } else if (x + y == 2) {
            // (0,2) -> (1,0) -> (0,2)
            // (2,0) -> (0,1) -> (2,0)
            // (1,1) -> (1,0)/(0,1) -> (1,1)
            return 2;
        }
        int min = Math.min(helper(Math.abs(x - 1), Math.abs(y - 2), dp),
                helper(Math.abs(x - 2), Math.abs(y - 1), dp)) + 1;
        dp.put(key, min);
        return min;
    }

    @Test
    public void test0() {
        int x = 5, y = 5;
        assertEquals(4, minKnightMoves(x, y));
    }
}
