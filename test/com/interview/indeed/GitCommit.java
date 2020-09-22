package com.interview.indeed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GitCommit {
    class Node {
        int id;
        List<Node> parents;

        public Node(int id) {
            this.id = id;
            this.parents = new ArrayList<>();
        }
    }

    // Q1. Find all commits
    public List<Node> getAllParents(Node root) {
        List<Node> res = new ArrayList<>();
        if (root == null || root.parents.size() == 0)
            return res;

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        queue.add(root);
        visited.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            for (Node each : cur.parents) {
                if (!visited.contains(each)) {
                    queue.add(each);
                    visited.add(each);
                    res.add(each);
                }
            }
        }
        return res;
    }

    // Q2. Find LCA of given 2 commits
    public Node getLCA(Node n1, Node n2) {
        if (n1 == null || n2 == null || n1 == n2)
            return null;

        Set<Node> visited1 = new HashSet<>();
        Queue<Node> q1 = new LinkedList<>();
        q1.add(n1);
        visited1.add(n1);

        Set<Node> visited2 = new HashSet<>();
        Queue<Node> q2 = new LinkedList<>();
        q2.add(n2);
        visited2.add(n2);

        // every time discover one level
        while (!q1.isEmpty() || !q2.isEmpty()) {
            int size1 = q1.size(); // traverse by level
            for (int i = 0; i < size1; i++) {
                Node node = q1.poll();
                for (Node next : node.parents) {
                    if (visited2.contains(next))
                        return next;

                    if (!visited1.contains(next)) {
                        visited1.add(next);
                        q1.add(next);
                    }
                }
            }

            int size2 = q2.size();
            for (int i = 0; i < size2; i++) {
                Node node = q2.poll();
                for (Node next : node.parents) {
                    if (visited1.contains(next))
                        return next;

                    if (!visited2.contains(next)) {
                        visited2.add(next);
                        q2.add(next);
                    }
                }
            }
        }
        return null;
    }
}

