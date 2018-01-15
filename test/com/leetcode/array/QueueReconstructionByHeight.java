package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        // Desc height, asec position
        Arrays.sort(people, (a, b) -> (a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]));
        List<int[]> res = new ArrayList<>();
        for(int[] p : people) {
            res.add(p[1], p);
        }
        return res.toArray(new int[people.length][2]);
    }

    @Test
    public void test0() {
        int[][] people = new int[][] { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
        int[][] expected = new int[][] { { 5, 0 }, { 7, 0 }, { 5, 2 }, { 6, 1 }, { 4, 4 }, { 7, 1 } };
        int[][] actual = reconstructQueue(people);
        for (int i = 0; i < people.length; ++i) {
            assertEquals(expected[i][0], actual[i][0]);
            assertEquals(expected[i][1], actual[i][1]);
        }
    }
}
