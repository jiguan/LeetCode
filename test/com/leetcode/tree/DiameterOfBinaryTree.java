package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class DiameterOfBinaryTree {
    int maxDepth = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDepth;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        maxDepth = Math.max(maxDepth, left + right);
        return 1 + Math.max(left, right);
    }

    @Test
    public void test0() {
        TreeNode root = null;
        assertEquals(0, diameterOfBinaryTree(root));
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(0);
        assertEquals(0, diameterOfBinaryTree(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.build(new Integer[] { 1, 2, 3, 4, 5 });
        assertEquals(3, diameterOfBinaryTree(root));
    }
}
