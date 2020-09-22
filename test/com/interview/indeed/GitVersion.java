package com.interview.indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GitVersion {
    public List<Integer> findCommits(GraphNode root) {
        List<Integer> result = new ArrayList<>();
        Set<GraphNode> visited = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();
            if (!visited.contains(curr)) {
                visited.add(curr);
                result.add(curr.val);

                for (GraphNode neighbor : curr.neighbors) {
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    private GraphNode buildGraph(int[][] commits) {
        if (commits == null || commits.length == 0) {
            return null;
        }

        // step 1: constrcut the graph
        // commit version, commit node
        Map<Integer, GraphNode> map = new HashMap<>();

        for (int[] commit : commits) {
            int from = commit[0];
            int to = commit[1];

            GraphNode fromNode;
            if (map.containsKey(from)) {
                fromNode = map.get(from);
            } else {
                fromNode = new GraphNode(from);
                map.put(from, fromNode);
            }

            GraphNode toNode;
            if (map.containsKey(to)) {
                toNode = map.get(to);
            } else {
                toNode = new GraphNode(to);
                map.put(to, toNode);
            }
            fromNode.neighbors.add(toNode);
        }

        // Step 2: find out the root of the graph
        GraphNode root = null;
        Map<GraphNode, Integer> indgree = new HashMap<>();
        for (GraphNode node : map.values()) {
            if (!indgree.containsKey(node)) {
                indgree.put(node, 0);
            }

            for (GraphNode neighbor : node.neighbors) {
                indgree.put(neighbor, indgree.getOrDefault(neighbor, 0) + 1);
            }
        }

        for (GraphNode node : indgree.keySet()) {
            if (indgree.get(node) == 0) {
                root = node;
                break;
            }
        }

        System.out.println("Root is " + root.val);
        return root;
    }

    public static void main(String[] args) {
        GitVersion sol = new GitVersion();
        int[][] commits = new int[][] {{0, 1}, {1, 3}, {3, 5}, {0, 2}, {2, 4}, {4, 5}};

        GraphNode root = sol.buildGraph(commits);

        List<Integer> result = sol.findCommits(root);

        for (Integer elem : result) {
            System.out.println(elem);
        }
    }

    class GraphNode {
        int val;
        List<GraphNode> neighbors;
        GraphNode parent;

        public GraphNode(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }
}
