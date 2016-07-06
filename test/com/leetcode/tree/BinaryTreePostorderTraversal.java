package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        List<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }

        Collections.reverse(res);
        return res;
    }

    @Test
    public void test0() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        List<Integer> expected = Arrays.asList(4, 2, 5, 3, 1);
        assertEquals(expected, postorderTraversal(root));
    }
}
