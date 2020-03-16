package com.leetcode.graph.prerequisite;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class CriticalConnectionsInANetwork {
    // We record the timestamp that we visit each node. For each node, we check
    // every neighbor except its parent and return a smallest timestamp in all its
    // neighbors. If this timestamp is strictly less than the node's timestamp, we
    // know that this node is somehow in a cycle. Otherwise, this edge from the
    // parent to this node is a critical connection

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> conn : connections) {
            if (!graph.containsKey(conn.get(0))) {
                graph.put(conn.get(0), new ArrayList<>());
            }
            graph.get(conn.get(0)).add(conn.get(1));
            if (!graph.containsKey(conn.get(1))) {
                graph.put(conn.get(1), new ArrayList<>());
            }
            graph.get(conn.get(1)).add(conn.get(0));
        }

        int[] indexes = new int[n];
        List<List<Integer>> res = new ArrayList<>();
        dfs(graph, 0, 0, 1, indexes, res);
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int currNode, int parentNode, int currIndex,
            int[] indexes, List<List<Integer>> res) {
        // this node is visited
        if (indexes[currNode] > 0) return;
        indexes[currNode] = currIndex;
        for (int next : graph.getOrDefault(currNode, new ArrayList<>())) {
            if (next == parentNode) continue;
            dfs(graph, next, currNode, currIndex + 1, indexes, res);
            if (currIndex < indexes[next]) {
                res.add(Arrays.asList(currNode, next));
            }
            indexes[currNode] = Math.min(indexes[currNode], indexes[next]);
        }
    }

    @Test
    public void test0() {
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2),
                Arrays.asList(0, 2), Arrays.asList(3, 1));
        /* @formatter:off
         *     3
         *     |
         *     1
         *    / \
         *   0 - 2
         * @formatter:on
         */
        List<List<Integer>> res = criticalConnections(n, connections);
        Set<List<Integer>> expected = new HashSet<>(Arrays.asList(Arrays.asList(1, 3)));
        for (List<Integer> edge : res) {
            Collections.sort(edge);
            assertTrue(expected.contains(edge));
        }
    }

    @Test
    public void test2() {
        int n = 4;
        List<List<Integer>> connections =
                Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(1, 3));
        /* @formatter:off
         *     3
         *     |
         *     1
         *    / \
         *   0   2
         * @formatter:on
         */
        List<List<Integer>> res = criticalConnections(n, connections);
        Set<List<Integer>> expected = new HashSet<>(
                Arrays.asList(Arrays.asList(1, 3), Arrays.asList(0, 1), Arrays.asList(1, 2)));
        for (List<Integer> r : res) {
            Collections.sort(r);
            assertTrue(expected.contains(r));
        }
    }

    @Test
    public void test3() {
        int n = 6;
        List<List<Integer>> connections = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2),
                Arrays.asList(2, 5), Arrays.asList(2, 3), Arrays.asList(4, 3));
        /* @formatter:off
         *     0
         *     |
         *     1
         *     |
         *     2
         *    / \
         *   5   3
         *       |
         *       4
         * @formatter:on
         */
        List<List<Integer>> res = criticalConnections(n, connections);
        Set<List<Integer>> expected =
                new HashSet<>(Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2),
                        Arrays.asList(2, 5), Arrays.asList(2, 3), Arrays.asList(3, 4)));
        for (List<Integer> r : res) {
            Collections.sort(r);
            assertTrue(expected.contains(r));
        }
    }
}
