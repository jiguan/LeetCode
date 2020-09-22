package com.interview.pinterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LowestCommonAncestry {

    static void graph(int[][] edges, int i1, int i2, int i3) {

        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> parent = new HashMap<>();

        for (int[] edge : edges) {
            if (!inDegree.containsKey(edge[0])) {
                inDegree.put(edge[0], 0);
                parent.put(edge[0], new ArrayList<Integer>());
            }
            if (!inDegree.containsKey(edge[1])) {
                inDegree.put(edge[1], 0);
                parent.put(edge[1], new ArrayList<Integer>());
            }
            inDegree.put(edge[1], inDegree.getOrDefault(edge[1], 0) + 1);
            parent.get(edge[1]).add(edge[0]);
        }

        // problem # 1 find nodes
        List<Integer> zeroParent = new ArrayList<>();
        List<Integer> oneParent = new ArrayList<>();

        for (Integer node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                zeroParent.add(node);
            } else if (inDegree.get(node) == 1) {
                oneParent.add(node);
            }
        }

        print("zero", zeroParent);
        print("one", oneParent);

        System.out.println("common " + i1 + " " + i2 + " " + common(i1, i2, parent));
        System.out.println("lowest common " + i1 + " " + i2 + " " + lowestCommon(i1, i2, parent));
        System.out.println("furthest " + i3 + " " + furthest(i3, parent));
    }

    static int lowestCommon(Integer i1, Integer i2, Map<Integer, List<Integer>> parent) {
        Set<Integer> parent1 = new HashSet<>();
        getAll(i1, parent1, parent);
        if (parent1.contains(i2)) {
            return i2;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(i2);
        int size = q.size();
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (parent1.contains(cur)) {
                return cur;
            }
            for (Integer p : parent.get(cur)) {
                q.offer(p);
            }
        }
        return -1;
    }

    static int furthest(Integer i, Map<Integer, List<Integer>> parent) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        int size = q.size();
        int ances = i;
        while (!q.isEmpty()) {
            ances = q.peek();
            while (size-- > 0) {
                int cur = q.poll();
                for (Integer p : parent.get(cur)) {
                    q.offer(p);
                }
            }
            size = q.size();
        }
        return ances;
    }

    static boolean common(Integer i1, Integer i2, Map<Integer, List<Integer>> parent) {
        Set<Integer> parent1 = new HashSet<>();
        getAll(i1, parent1, parent);
        Set<Integer> parent2 = new HashSet<>();
        getAll(i2, parent2, parent);

        for (Integer i : parent1) {
            if (parent2.contains(i)) {
                return true;
            }
        }
        return false;
    }

    static void getAll(Integer i, Set<Integer> parents, Map<Integer, List<Integer>> parent) {
        parents.add(i);
        if (!parent.containsKey(i) || parent.get(i).isEmpty()) {
            return;
        }
        for (Integer p : parent.get(i)) {
            getAll(p, parents, parent);
        }
    }

    static void print(String s, List<Integer> list) {
        System.out.println(s);
        for (Integer i : list) {
            System.out.print(i + " --> ");
        }
        System.out.println();
    }

    // Driver Code
    public static void main(String args[]) {
        int[][] edges = new int[][] {{1, 4}, {1, 5}, {2, 5}, {3, 6}, {6, 7}};
        graph(edges, 4, 3, 7);
        graph(edges, 4, 5, 7);
        graph(edges, 6, 7, 7);
        graph(edges, 3, 7, 7);

    }
}
