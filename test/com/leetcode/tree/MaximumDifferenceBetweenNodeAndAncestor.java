package com.leetcode.tree;

import com.leetcode.util.TreeNode;

public class MaximumDifferenceBetweenNodeAndAncestor {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode node, int min, int max) {
        if (node == null) return -1;

        int tmp = Math.max(Math.abs(node.val - min), Math.abs(node.val - max));

        min = Math.min(min, node.val);
        max = Math.max(max, node.val);
        return Math.max(Math.max(dfs(node.left, min, max), dfs(node.right, min, max)), tmp);
    }

// @formatter:off
//    public int dfs(TreeNode root, int min, int max) {
//        if (root == null) return max - min;
//        max = Math.max(max, root.val);
//        min = Math.min(min, root.val);
//        return Math.max(dfs(root.left, min, max), dfs(root.right, min, max));
//    }
// @formatter:on
}
