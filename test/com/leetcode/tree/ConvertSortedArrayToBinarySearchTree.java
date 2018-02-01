package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return traverse(nums, 0, nums.length - 1);
    }

    private TreeNode traverse(int[] nums, int low, int high) {
        if (low > high) return null;

        int mid = (low + high + 1) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = traverse(nums, low, mid - 1);
        node.right = traverse(nums, mid + 1, high);

        return node;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode actual = sortedArrayToBST(nums);
        TreeNode expected = TreeNode.build(new Integer[]{0, -3, 9, -10, null, 5});
        assertTrue(TreeNode.isSameTree(expected, actual));
    }
}
