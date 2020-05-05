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
        for (int[] tran : transactions) {
            int from = tran[0], to = tran[1], amt = tran[2];
            map.put(from, map.getOrDefault(from, 0) - amt);
            map.put(to, map.getOrDefault(to, 0) + amt);
        }
        return settle(0, new ArrayList<>(map.values()));
    }

    private int settle(int index, List<Integer> balance) {
        while (index < balance.size() && balance.get(index) == 0) {
            index++;
        }
        if (index == balance.size()) return 0;
        // how many transactions needed
        int times = Integer.MAX_VALUE;
        for (int i = index + 1; i < balance.size(); i++) {
            if (balance.get(index) * balance.get(i) < 0) {
                balance.set(i, balance.get(i) + balance.get(index));
                // move to next non-zero amount
                times = Math.min(times, 1 + settle(index + 1, balance));
                balance.set(i, balance.get(i) - balance.get(index));
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
