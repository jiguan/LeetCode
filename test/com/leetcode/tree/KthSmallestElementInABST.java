package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.leetcode.util.TreeNode;

public class KthSmallestElementInABST {
    private int res = 0;
    private int counter = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }

    private void inorder(TreeNode root, int k) {
        if (root == null) return;
        inorder(root.left, k);
        ++counter;
        if (counter == k) {
            res = root.val;
        }
        inorder(root.right, k);
    }

    @Test
    public void test0() {
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        root.left = node2;
        root.right = node8;
        node2.left = node0;
        node2.right = node4;
        node4.left = node3;
        node4.right = node5;
        node8.left = node7;
        node8.right = node9;
        assertEquals(2, kthSmallest(root, 2));
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;
        assertEquals(2, kthSmallest(root, 2));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        root.left = left;
        assertEquals(1, kthSmallest(root, 1));
    }

    @Test
    public void test3() {
        TreeNode root = TreeNode.build(new Integer[] {3, 1, 4, null, 2});
        int k = 1;
        assertEquals(Integer.valueOf(1), Integer.valueOf(kthSmallest(root, k)));
    }
}
