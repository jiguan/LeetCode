package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        // user - amt
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            int from = t[0], to = t[1], amt = t[2];
            map.put(from, map.getOrDefault(from, 0) - amt);
            map.put(to, map.getOrDefault(to, 0) + amt);
        }
        return settle(0, new ArrayList<>(map.values()));
    }

    private int settle(int start, List<Integer> debt) {
        while (start < debt.size() && debt.get(start) == 0) {
            start++;
        }
        if (start == debt.size()) return 0;
        // how many transactions needed
        int times = Integer.MAX_VALUE;
        for (int i = start + 1; i < debt.size(); i++) {
            if (debt.get(start) * debt.get(i) < 0) {
                debt.set(i, debt.get(i) + debt.get(start));
                times = Math.min(times, 1 + settle(start + 1, debt));
                debt.set(i, debt.get(i) - debt.get(start));
            }
        }

        return times;
    }

    @Test
    public void test0() {
        int[][] transactions = {{0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}};
        assertEquals(1, minTransfers(transactions));
    }
}
