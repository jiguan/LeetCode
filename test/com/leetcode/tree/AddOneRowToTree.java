package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class AddOneRowToTree {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 0 || d == 1) {
            TreeNode newRoot = new TreeNode(v);
            // default behavior: when d == 1, insert root to the left
            if (d == 1) newRoot.left = root;
            else newRoot.right = root;
            return newRoot;
        } else if (root != null) {
            root.left = addOneRow(root.left, v, d > 2 ? d - 1 : 1);
            root.right = addOneRow(root.right, v, d > 2 ? d - 1 : 0);
        }

        return root;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] { 4, 2, null, 3, 1 });
        TreeNode expected = TreeNode.build(new Integer[] { 4, 2, null, 1, 1, 3, null, null, 1 });
        int v = 1, d = 3;
        assertTrue(TreeNode.isSameTree(expected, addOneRow(root, v, d)));
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[] { 4, 2, 6, 3, 1, 5 });
        TreeNode expected = TreeNode.build(new Integer[] { 4, 1, 1, 2, null, null, 6, 3, 1, 5 });
        int v = 1, d = 2;
        assertTrue(TreeNode.isSameTree(expected, addOneRow(root, v, d)));
    }
}
