package com.interview.indeed;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.Test;
import com.leetcode.util.TreeNode;

public class MinPathFromTopToLeaf {

    @Test
    public void testBFS() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);

        List<Integer> expected = Arrays.asList(1, 2, 5);
        MinPathBFS bfs = new MinPathBFS();
        assertEquals(8, bfs.bfs(root));
        assertEquals(expected, bfs.minPath);
    }

    @Test
    public void testDFS() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);

        List<Integer> expected = Arrays.asList(1, 2, 5);
        MinPathDFS dfs = new MinPathDFS();
        assertEquals(8, dfs.dfs(root));
        assertEquals(expected, dfs.minPath);
    }

    /*
     * @formatter:off
     *      #
     *    1/ \2
     *    #   #
     *    3\ /1\4 
     *      #   #
     *           \5
     *            #
     * @formatter:on
     */

    @Test
    public void testDAG() {
        Node root = new Node();
        root.addNode(new Node(), 1);
        root.addNode(new Node(), 2);
        Node common = new Node();
        root.getNode(1).addNode(common, 3);
        root.getNode(2).addNode(common, 1);
        root.getNode(2).addNode(new Node(), 4);
        root.getNode(2).getNode(4).addNode(new Node(), 5);
        MinPathDAG dag = new MinPathDAG();
        assertEquals(3, dag.dfs(root));
        List<Integer> expected = Arrays.asList(2, 1);
        assertEquals(expected, dag.minPath);
    }
}


class MinPathBFS {
    List<Integer> minPath = new LinkedList<>();

    public int bfs(TreeNode root) {
        if (root == null)
            return -1;

        int minCost = Integer.MAX_VALUE;
        TreeNode minLeaf = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // children, current nodes sum
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 0);
        // current node, parent node
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        parents.put(root, null);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int sum = map.remove(node) + node.val;
            if (sum >= minCost)
                continue;

            if (node.left == null && node.right == null) {
                if (sum < minCost) {
                    minCost = sum;
                    minLeaf = node;
                }
            } else {
                if (node.left != null) {
                    queue.add(node.left);
                    map.put(node.left, sum);
                    parents.put(node.left, node);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    map.put(node.right, sum);
                    parents.put(node.right, node);
                }
            }
        }

        while (minLeaf != null) {
            minPath.add(0, minLeaf.val);
            minLeaf = parents.get(minLeaf);
        }
        return minCost;
    }
}


class MinPathDFS {
    int minCost = Integer.MAX_VALUE;
    List<Integer> minPath = null;

    public int dfs(TreeNode root) {
        if (root == null)
            return -1;
        helper(root, 0, new ArrayList<>());
        return minCost;
    }

    private void helper(TreeNode node, int sum, List<Integer> path) {
        // early quit if all values are guaranteed to be positive
        if (node == null || sum >= minCost)
            return;
        sum += node.val;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            if (sum < minCost) {
                minCost = sum;
                minPath = new ArrayList<>(path);
            }
        } else {
            helper(node.left, sum, path);
            helper(node.right, sum, path);
        }
        path.remove(path.size() - 1);
    }
}
// Change to DAG, above O(V!)
// Below, O(V + E)
class MinPathDAG {
    List<Integer> minPath = null;
    Map<Node, Integer> map = new HashMap<>();
    int minCost = Integer.MAX_VALUE;

    public int dfs(Node root) {
        if (root == null)
            return -1;
        helper(root, 0, new ArrayList<>());
        return minCost;
    }

    private void helper(Node node, int sum, List<Integer> path) {
        if (node == null) {
            return;
        }
        if (map.containsKey(node) && sum >= map.get(node)) {
            return;
        }

        map.put(node, sum);
        if (node.edges.isEmpty()) {
            if (sum < minCost) {
                minCost = sum;
                minPath = new ArrayList<>(path);
            }
        } else {
            for (Edge edge : node.edges) {
                path.add(edge.cost);
                helper(edge.node, sum + edge.cost, path);
                path.remove(path.size() - 1);
            }
        }
    }
}


class Edge {
    Node node;
    int cost;

    Edge(Node node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return cost + "=>" + node;
    }
}


class Node {
    List<Edge> edges = new ArrayList<>();

    public void addNode(Node node, int cost) {
        Edge edge = new Edge(node, cost);
        edges.add(edge);
    }

    public Node getNode(int cost) {
        for (Edge edge : edges) {
            if (edge.cost == cost) {
                return edge.node;
            }
        }
        return null;
    }

}
