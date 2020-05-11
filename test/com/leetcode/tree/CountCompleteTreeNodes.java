package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.leetcode.util.TreeNode;

public class CountCompleteTreeNodes {

    // check if root is a complete tree
    public int countNodes0(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode leftNode = root;
        int leftLen = 0;
        while (leftNode.left != null) {
            leftLen++;
            leftNode = leftNode.left;
        }
        int rightLen = 0;
        TreeNode rightNode = root;
        while (rightNode.right != null) {
            rightLen++;
            rightNode = rightNode.right;
        }
        if (leftLen == rightLen) {
            return (2 << leftLen) - 1;
        } else {
            return 1 + countNodes0(root.left) + countNodes0(root.right);
        }
    }

    // Repeat O(log(n)) times
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        // get leftmost height
        int h = height(root);
        int count = 0;
        // root's left subtree is a complete tree
        if (height(root.right) == h - 1) {
            // count = left subtree: 2^h - 1 + root + right subtree
            // Because height only consider the left node, we don't know right subtree is
            // complete or not
            count = (1 << h) + countNodes(root.right);
        } else {
            // right subtree: 2 ^ (h - 1) + left subtree
            count = (1 << h - 1) + countNodes(root.left);
        }
        return count;
    }

    // Calculate the height of complete tree, O(log(n))
    private int height(TreeNode node) {
        return node == null ? -1 : 1 + height(node.left);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] {1, 2, 3, 4, 5, 6});
        assertEquals(6, countNodes(root));
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(0);
        assertEquals(1, countNodes(root));
    }
}
