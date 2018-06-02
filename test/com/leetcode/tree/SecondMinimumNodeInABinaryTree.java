package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class SecondMinimumNodeInABinaryTree {
    // Each node in this tree has exactly two or zero sub-node.
    // If the node has two sub-nodes, then this node's value is the smaller
    // value among its two sub-nodes.
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;

        if (root.left == null && root.right == null) return -1;

        int left = root.left.val, right = root.right.val;

        if (root.left.val == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.right.val == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        // left or right, has sub-children
        if (left == -1 || right == -1) {
            return Math.max(left, right);
        }
        
        // Since root is larger than its children, just return the smaller child
        return Math.min(left, right);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{2,2,2, null, null, 5 ,7});
        assertEquals(5, findSecondMinimumValue(root));
    }

}
