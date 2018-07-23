package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class InorderSuccessorInBST {
    // https://www.lintcode.com/problem/inorder-successor-in-bst/description
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null) return null;
        TreeNode prev = null;
        TreeNode node = root;
        while (node != null && node.val != p.val) {
            if (node.val > p.val) {
                prev = node;
                node = node.left;
            } else {
                // since we only care successor, prev (smaller) one doesn't matter
                node = node.right;
            }
        }
        if (node == null) return null;
        // there is no right nodes (larger)
        if (node.right == null) return prev;

        // there is right nodes, find the node with min value
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{2, 1, 3});
        TreeNode p = new TreeNode(1);
        assertEquals(2, inorderSuccessor(root, p).val);
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[]{2, 1});
        TreeNode p = new TreeNode(1);
        assertEquals(2, inorderSuccessor(root, p).val);
    }
}
