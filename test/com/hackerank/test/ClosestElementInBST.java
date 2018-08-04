package com.hackerank.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class ClosestElementInBST {
    // find the element in a BST which is closest to the target value
    public TreeNode find(TreeNode root, int target) {
        int diff = Math.abs(root.val - target);
        TreeNode res = root;
        if (root.val > target && root.left != null) {
            TreeNode left = find(root.left, target);
            if (Math.abs(left.val - target) < diff) {
                res = left;
            }
        } else if (root.val < target && root.right != null) {
            TreeNode right = find(root.right, target);
            if (Math.abs(right.val - target) < diff) {
                res = right;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{30, 10, 50, null, 20, 40, 60});
        int target = 49;
        assertEquals(50, find(root, target).val);
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[]{30, 10, 50, null,20, 40, 60});
        int target = 51;
        assertEquals(50, find(root, target).val);
    }
}
