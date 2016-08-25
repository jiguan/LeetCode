package com.leetcode.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tree {
    public static void preorder(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    // Look closely, it is basically same with pre-order.
    // The difference are: 1. t push to left
    // first, right later; 2. reverse list in the end
    public static void postorder(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void inorder(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = root;

        while (!stack.isEmpty() || prev != null) {
            while (prev != null) {
                stack.push(prev);
                prev = prev.left;
            }

            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            prev = node.right;
        }
    }

}
