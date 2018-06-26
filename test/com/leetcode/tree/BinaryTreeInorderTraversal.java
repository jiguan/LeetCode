package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        // store current node's left children
        // Initially it is unknown
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            // as long as there is a valid node, we need to explore its left children
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // all its left children have been traversed
            res.add(node.val);
            node = node.right;
        }

        return res;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<Integer> expected = Arrays.asList(3, 2, 4, 1, 6, 5, 7);
        assertEquals(expected, inorderTraversal(root));
    }
}
