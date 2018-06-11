package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RabbitsInForest {
    // Answers tell you how many other rabbits have the same color as them.
    // Return the minimum number of rabbits that could be in the forest.
    public int numRabbits(int[] answers) {
        // If x+1 rabbits have same color, then we get x+1 rabbits who all
        // answer x
        Map<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        int res = 0;
        for (int i : map.keySet()) {
            res += (map.get(i) + i) / (i + 1) * (i + 1);
        }
        return res;
    }

    @Test
    public void test0() {
        int expected = 5;
        int[] answers = new int[]{1, 1, 2};
        assertEquals(Integer.valueOf(expected), Integer.valueOf(numRabbits(answers)));
    }
}
