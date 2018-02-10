package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class KdiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return 0;
        // num - times
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() > 1) {
                    res++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    res++;
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[] { 1, 2, 3, 4, 5 };
        int k = 1;
        assertEquals(4, findPairs(nums, k));
    }

    @Test
    public void test1() {
        int[] nums = new int[] { 1, 2, 3, 4, 5 };
        int k = -1;
        assertEquals(0, findPairs(nums, k));
    }

    @Test
    public void test3() {
        int[] nums = new int[] { 1, 1, 1, 1, 1 };
        int k = 0;
        assertEquals(1, findPairs(nums, k));
    }
}
