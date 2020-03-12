package com.interview.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 1. Find elements with 0 or 1 ancestors
 * 
 * 2. Check if two elements have common ancestors
 * 
 * 3. Find the farest ancestor of one element
 */
public class Ancestors {
    public void buildGraph(int[][] edges) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        // node - its ancestors
        Map<Integer, List<Integer>> parents = new HashMap<>();

        for (int[] edge : edges) {
            if (!inDegree.containsKey(edge[0])) {
                inDegree.put(edge[0], 0);
                parents.put(edge[0], new ArrayList<Integer>());
            }
            if (!inDegree.containsKey(edge[1])) {
                inDegree.put(edge[1], 0);
                parents.put(edge[1], new ArrayList<Integer>());
            }
            inDegree.put(edge[1], inDegree.getOrDefault(edge[1], 0) + 1);
            // edge[1]'s parent is edge[0]
            parents.get(edge[1]).add(edge[0]);
        }
    }

    // # 1
    public List<Integer> findNodeWithParent(int parentNum, Map<Integer, Integer> inDegree) {
        List<Integer> res = new ArrayList<Integer>();
        for (Integer node : inDegree.keySet()) {
            if (inDegree.get(node) == parentNum) {
                res.add(node);
            }
        }
        return res;
    }

    // # 2
    public boolean findCommonAncestor(int num1, int num2, Map<Integer, List<Integer>> parents) {
        Set<Integer> parent1 = findAllParents(num1, parents);
        Set<Integer> parent2 = findAllParents(num2, parents);

        for (int p1 : parent1) {
            if (parent2.contains(p1)) {
                return true;
            }
        }
        return false;
    }

    private Set<Integer> findAllParents(int num, Map<Integer, List<Integer>> parents) {
        Set<Integer> res = new HashSet<>();
        if (!parents.containsKey(num) || parents.get(num).isEmpty()) {
            return res;
        }
        for (int p : parents.get(num)) {
            res.addAll(findAllParents(p, parents));
        }
        return res;
    }

    public int findLowestCommon(int num1, int num2, Map<Integer, List<Integer>> parents) {
        Set<Integer> parent1 = findAllParents(num1, parents);
        if (parent1.contains(num2)) {
            return num2;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num2);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (parent1.contains(cur)) {
                return cur;
            }
            for (int p : parents.get(cur)) {
                queue.offer(p);
            }
        }
        return -1;
    }

    // # 3
    public int findFurthese(int num, Map<Integer, List<Integer>> parents) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        int res = num;

        while (!queue.isEmpty()) {
            int size = queue.size();
            res = queue.peek();
            while (size-- > 0) {
                int cur = queue.poll();
                for (int p : parents.get(cur)) {
                    queue.add(p);
                }
            }
        }
        return res;
    }
}
