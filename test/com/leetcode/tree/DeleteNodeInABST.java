package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class DeleteNodeInABST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode min = findMin(root.right);
            root.val = min.val;

            root.right = deleteNode(root.right, min.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{5, 3, 6, 2, 4, null, 7});
        TreeNode expect = TreeNode.build(new Integer[]{5, 4, 6, 2, null, null, 7});
        TreeNode actual = deleteNode(root, 3);
        assertTrue(TreeNode.isSameTree(expect, actual));
    }
}
