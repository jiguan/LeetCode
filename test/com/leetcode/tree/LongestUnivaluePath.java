package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class LongestUnivaluePath {
    int maxLen = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        traverse(root, root.val);
        return maxLen;
    }

    private int traverse(TreeNode node, int parent) {
        if (node == null) return 0;

        int left = traverse(node.left, node.val);
        int right = traverse(node.right, node.val);

        maxLen = Math.max(maxLen, left + right);
        
        if(node.val == parent) return Math.max(left, right) + 1;
        else return 0;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] { 5, 4, 5, 1, 1, null, 5 });
        assertEquals(Integer.valueOf(2), Integer.valueOf(longestUnivaluePath(root)));
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[] { 1, 4, 5, 4, 4, null, 5 });
        assertEquals(Integer.valueOf(2), Integer.valueOf(longestUnivaluePath(root)));
    }
}
