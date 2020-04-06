package com.leetcode.dp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class FrogJump {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if (n == 0 || stones[0] != 0 || stones[1] != 1) return false;

        // stone we can reach - the step we use to reach it
        Map<Integer, Set<Integer>> reach = new HashMap<>();
        for (int i = 0; i < stones.length; ++i) {
            reach.put(stones[i], new HashSet<>());
        }
        reach.get(0).add(1);

        for (int i = 0; i < n; ++i) {
            int stone = stones[i];
            // get last step k
            for (int k : reach.get(stone)) {
                for (int step = k - 1; step < k + 1; ++step) {
                    if (step > 0 && reach.containsKey(stone + step)) {
                        reach.get(stone + step).add(step);
                    }
                }
            }
        }
        return reach.get(stones[stones.length - 1]).size() > 0;
    }

    @Test
    public void test0() {
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        assertTrue(canCross(stones));
    }

    @Test
    public void test1() {
        int[] stones = {0, 1, 2, 3, 4, 8, 9, 11};
        assertFalse(canCross(stones));
    }
}
