package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class MinimumAbsoluteDifferenceInBST {
    TreeNode prev = null;
    int diff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        inorder(root);
        return diff;
    }

    private void inorder(TreeNode node) {
        if (node.left != null) inorder(node.left);
        if (prev != null) diff = Math.min(diff, node.val - prev.val);
        prev = node;
        if (node.right != null) inorder(node.right);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] { 1, null, 4, 2 });
        assertEquals(1, getMinimumDifference(root));
    }
}
