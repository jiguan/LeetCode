package com.leetcode.bfs;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/* 
 * Initially, the frog is on the first stone and assume the first jump must be 1 unit. 
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units.
 */
public class FrogJump {
    public boolean canCross(int[] stones) {
        if (stones.length == 0) return true;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; ++i) {
            map.put(stones[i], new HashSet<>());
        }
        map.get(0).add(1);

        for (int i = 0; i < stones.length; ++i) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int reachable = stone + step;
                if (reachable == stones[stones.length - 1]) {
                    return true;
                }
                Set<Integer> set = map.get(reachable);
                if (set != null) {
                    set.add(step);
                    set.add(step + 1);
                    if (step - 1 > 0) set.add(step - 1);
                }
            }
        }
        return false;
    }

    public boolean canCross0(int[] stones) {
        return dfs(stones, 0, 1);
    }

    private boolean dfs(int[] stones, int cur, int unit) {
        if (unit <= 0) return false;
        if (cur == stones.length - 1) return true;

        for (int i = cur + 1; i < stones.length; ++i) {
            if (stones[i] == stones[cur] + unit) {
                return dfs(stones, i, unit + 1) || dfs(stones, i, unit - 1) || dfs(stones, i, unit);
            }
        }

        return false;
    }

    @Test
    public void test0() {
        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        assertTrue(canCross(stones));
    }
}
