package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MinumumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Arrays.asList(0);

        // node - adjacent nodes
        Map<Integer, Set<Integer>> adjacent = new HashMap<Integer, Set<Integer>>();

        for (int[] edge : edges) {
            int i = edge[0], j = edge[1];

            if (!adjacent.containsKey(i)) {
                adjacent.put(i, new HashSet<>());
            }
            if (!adjacent.containsKey(j)) {
                adjacent.put(j, new HashSet<>());
            }
            adjacent.get(i).add(j);
            adjacent.get(j).add(i);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (adjacent.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();

            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adjacent.get(i).iterator().next();
                adjacent.get(j).remove(i);
                if (adjacent.get(j).size() == 1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }

        return leaves;
    }

    @Test
    public void test0() {
        int n = 6;
        int[][] edges = new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        assertEquals(Arrays.asList(3, 4), findMinHeightTrees(n, edges));
    }

    @Test
    public void test1() {
        int n = 4;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {1, 3}};
        assertEquals(Arrays.asList(1), findMinHeightTrees(n, edges));
    }

    @Test
    public void test2() {
        int n = 10;
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 4}, {0, 5}, {5, 6}, {6, 7}, {2, 8}, {7, 9}};
        assertEquals(Arrays.asList(5), findMinHeightTrees(n, edges));
    }
}
