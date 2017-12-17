package com.leetcode.util;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.format("TreeNode(%d)", val);
    }

    public static TreeNode build(Integer[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 0;
        while (i < nums.length) {
            TreeNode left = (++i >= nums.length || nums[i] == null) ? null : new TreeNode(nums[i]);
            TreeNode right = (++i >= nums.length || nums[i] == null) ? null : new TreeNode(nums[i]);

            TreeNode node = queue.poll();
            node.left = left;
            node.right = right;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }

        return root;
    }

    public static boolean sameTree(TreeNode expect, TreeNode actual) {
        if (expect == null && actual == null) return true;
        if (expect == null || actual == null) return false;
        return expect.val == actual.val && sameTree(expect.left, actual.left) && sameTree(expect.right, actual.right);
    }

}
