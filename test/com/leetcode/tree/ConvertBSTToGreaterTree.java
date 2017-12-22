package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class ConvertBSTToGreaterTree {
    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    public int dfs(TreeNode node, int sum) {
        if (node == null) return sum;
        node.val = node.val + dfs(node.right, sum);
        return dfs(node.left, node.val);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{5, 2, 13});
        TreeNode actual = convertBST(root);
        TreeNode expect = TreeNode.build(new Integer[]{18, 20, 13});
        assertTrue(TreeNode.sameTree(expect, actual));
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[]{2, 0, 3, -4, 1});
        TreeNode actual = convertBST(root);
        TreeNode expect = TreeNode.build(new Integer[]{5, 6, 3, 2, 6});
        assertTrue(TreeNode.sameTree(expect, actual));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.build(new Integer[]{1, 0, 4, -2, null, 3});
        TreeNode actual = convertBST(root);
        TreeNode expect = TreeNode.build(new Integer[]{8, 8, 4, 6, null, 7});
        assertTrue(TreeNode.sameTree(expect, actual));
    }
}
