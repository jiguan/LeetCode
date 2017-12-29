package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class FlattenBinaryTreeToLinkedList {
    private TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if(root == null) return;
        
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        
        prev = root;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 5, 3, 4, null, 6});
        TreeNode expected = TreeNode.build(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6});
        flatten(root);
        assertTrue(TreeNode.sameTree(expected, root));
    }
}
