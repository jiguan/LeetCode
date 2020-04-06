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
 * @formatter:on
 */
public class CriticalRouters {
    static int time = 0;

    List<Integer> getCriticalNodes(int numRouters, int numLinks, List<List<Integer>> links) {
        time = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= numRouters; i++) {
            map.put(i, new HashSet<>());
        }
        for (List<Integer> link : links) {
            map.get(link.get(0)).add(link.get(1));
            map.get(link.get(1)).add(link.get(0));
        }
        Set<Integer> set = new HashSet<>();
        int[] low = new int[numRouters];
        // if it is visited
        int[] ids = new int[numRouters];
        int parent[] = new int[numRouters];
        Arrays.fill(ids, -1);
        Arrays.fill(parent, -1);
        for (int i = 1; i <= numRouters; i++) {
            if (ids[i - 1] == -1) dfs(map, low, ids, parent, i, set);
        }
        return new ArrayList<>(set);
    }


    void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int[] parent, int currNode,
            Set<Integer> res) {
        int children = 0;
        // record the timestamp
        ids[currNode - 1] = low[currNode - 1] = ++time;
        for (int nextNode : map.get(currNode)) {
            if(nextNode == parent[currNode - 1]) continue;
            if (ids[nextNode - 1] == -1) {
                children++;
                parent[nextNode - 1] = currNode;
                dfs(map, low, ids, parent, nextNode, res);
                low[currNode - 1] = Math.min(low[currNode - 1], low[nextNode - 1]);
                // at least 2 children node, and they are not connected together (we run dfs)
                if ((parent[currNode - 1] == -1 && children > 1)
                        // all its neighbors are deeper than this one
                        || (parent[currNode - 1] != -1 && ids[currNode - 1] <= low[nextNode - 1]))
                    res.add(currNode);
            } else {
                // found a loop, update the low to the visited node's id
                low[currNode - 1] = Math.min(low[currNode - 1], ids[nextNode - 1]);
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
        assertEquals(expected, new HashSet<>(getCriticalNodes(numRouters, numLinks, connections)));
    }

    @Test
    public void test1() {
        int numRouters = 6, numLinks = 5;
        // @formatter:off
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4),
                Arrays.asList(5, 4),
                Arrays.asList(3, 6));
        // @formatter:on
        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 4));
        assertEquals(expected, new HashSet<>(getCriticalNodes(numRouters, numLinks, connections)));
    }
    
    @Test
    public void test2() {
        int numRouters = 5, numLinks = 5;
        // @formatter:off
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(2, 4),
                Arrays.asList(3, 4),
                Arrays.asList(5, 4));
        // @formatter:on
        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 4));
        assertEquals(expected, new HashSet<>(getCriticalNodes(numRouters, numLinks, connections)));
    }
    
    @Test
    public void test3() {
        int numRouters = 4, numLinks = 3;
        // @formatter:off
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4));
        // @formatter:on
        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 4));
        assertEquals(expected, new HashSet<>(getCriticalNodes(numRouters, numLinks, connections)));
    }
    
    @Test
    public void test4() {
        int numRouters = 4, numLinks = 4;
        // @formatter:off
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(2, 4),
                Arrays.asList(3, 4));
        // @formatter:on
        // verify ids[cur] = low[next]
        Set<Integer> expected = new HashSet<>(Arrays.asList(2));
        assertEquals(expected, new HashSet<>(getCriticalNodes(numRouters, numLinks, connections)));
    }
    
    @Test
    public void test5() {
        int numRouters = 4, numLinks = 3;
        // @formatter:off
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(2, 4));
        // @formatter:on
        Set<Integer> expected = new HashSet<>(Arrays.asList(2, 3, 4));
        assertEquals(expected, new HashSet<>(getCriticalNodes(numRouters, numLinks, connections)));
    }
}
