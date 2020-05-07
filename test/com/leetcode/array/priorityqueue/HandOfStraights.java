package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertTrue;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

public class HandOfStraights {
    // each group is size W
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) return false;

        // key is sorted
        Map<Integer, Integer> map = new TreeMap<>();
        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int card = entry.getKey();
            int count = entry.getValue();
            if (count > 0) {
                for (int i = 0; i < W; ++i) {
                    int tmp = card + i;
                    if (!map.containsKey(tmp) || map.get(tmp) < count) {
                        return false;
                    }
                    map.put(tmp, map.get(tmp) - count);
                }
            }
        }

        return true;
    }

    @Test
    public void test0() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int W = 3;
        assertTrue(isNStraightHand(hand, W));
    }
}
