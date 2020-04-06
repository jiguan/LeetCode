package com.interview.amazon;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/*
 * You are given an undirected connected graph. An articulation point (or cut vertex) is defined as
 * a vertex which, when removed along with associated edges, makes the graph disconnected (or more
 * precisely, increases the number of connected components in the graph). The task is to find all
 * articulation points in the given graph.
 * 
 * Input: The input to the function/method consists of three arguments:
 * 
 * numNodes, an integer representing the number of nodes in the graph.
 * 
 * numEdges, an integer representing the number of edges in the graph.
 * 
 * edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
 * 
 * Output: Return a list of integers representing the critical nodes.
 */
/* @formatter:off
 * Input: numNodes = 7, numEdges = 7, edges = [[0, 1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3, 4]]
 * 
 *     4
 *    /
 *   3
 *  / \
 * 1   2
 *  \ / \
 *   0   5
 *      /
 *     6
 *
 * Output: [2, 3, 5]
 * @formatter: on
 */
public class CriticalRouters2 {
    List<Integer> criticalRouters(int numRouters, int numLinks, 
            List<List<Integer>> links) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> depths = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(List<Integer> link : links) {
            int A = link.get(0), B = link.get(1);
            if(!graph.containsKey(A)) {
                graph.put(A, new HashSet<>());
            }
            graph.get(A).add(B);
            indegree.put(A, indegree.getOrDefault(A, 0) + 1);
            depths.put(A, 0);
            if(!graph.containsKey(B)) {
                graph.put(B, new HashSet<>());
            }
            graph.get(B).add(A);
            indegree.put(B, indegree.getOrDefault(B, 0) + 1);
            depths.put(B, 0);
        }

        List<Integer> res = new ArrayList<>();
        dfs(graph, 1, 1, 1, depths, indegree, res);
        res.remove(Integer.valueOf(1));
        return res;
    }

    private void dfs(Map<Integer, Set<Integer>> graph, int currNode, int parentNode, int depth, Map<Integer, Integer> depths, Map<Integer, Integer> indegree, List<Integer> res) {
        if(depths.get(currNode) > 0) return;
        depths.put(currNode, depth);
        if(graph.containsKey(currNode)) {
            for(int nextNode : graph.get(currNode)) {
                if(nextNode == parentNode) continue;
                dfs(graph, nextNode, currNode, depth + 1, depths, indegree, res);
                if(depth < depths.get(nextNode)) {
                    // found a critical path, add nodes if they are not leaf
                    if(indegree.get(currNode) != 1) {
                        res.add(currNode);
                    }
                    if(indegree.get(nextNode) != 1) {
                        res.add(nextNode);
                    }
                }
                depths.put(currNode, Math.min(depths.get(currNode), depths.get(nextNode)));
            }
        }
    }

    @Test
    public void test0() {
        int numRouters = 7, numLinks = 7;
        // @formatter:off
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(2, 4),
                Arrays.asList(3, 4),
                Arrays.asList(3, 6),
                Arrays.asList(6, 7),
                Arrays.asList(4, 5));
        // @formatter:on
        Set<Integer> expected = new HashSet<>(Arrays.asList(3, 4, 6));
        assertEquals(expected, new HashSet<>(criticalRouters(numRouters, numLinks, connections)));
    }

    @Test
    public void test1() {
        int numRouters = 6, numLinks = 5;
        // @formatter:off
        /*
         *   1
         *   |
         *   2
         *   |
         *   3
         *  / \
         * 6   4
         *      \
         *       5
         */
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4),
                Arrays.asList(5, 4),
                Arrays.asList(3, 6));
        // @formatter:on
        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 4));
        assertEquals(expected, new HashSet<>(criticalRouters(numRouters, numLinks, connections)));
    }

    @Test
    public void test2() {
        int numRouters = 4, numLinks = 4;
        // @formatter:off
        /*
         *   1
         *   |
         *   2
         *  / \
         * 3 - 4
         */
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(2, 4),
                Arrays.asList(3, 4));
        // @formatter:on
        Set<Integer> expected = new HashSet<>(Arrays.asList(2));
        assertEquals(expected, new HashSet<>(criticalRouters(numRouters, numLinks, connections)));
    }
}
