package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) + dfs(node.left, sum - node.val) + dfs(node.right, sum - node.val);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1});
        assertEquals(3, pathSum(root, 8));
    }
}
