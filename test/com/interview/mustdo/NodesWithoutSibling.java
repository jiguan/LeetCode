package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class NodesWithoutSibling {
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && node.right != null) {
                stack.push(node.left);
                stack.push(node.right);
            } else if (node.left != null) {
                res.add(node.left.val);
                stack.push(node.left);
            } else if (node.right != null) {
                res.add(node.right.val);
                stack.push(node.right);
            }
        }
        return res;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 3, null, 4, 5, null, 6});
        Set<Integer> expected = new HashSet<>(Arrays.asList(4, 5, 6));
        assertEquals(expected, new HashSet<>(traverse(root)));
    }
}
