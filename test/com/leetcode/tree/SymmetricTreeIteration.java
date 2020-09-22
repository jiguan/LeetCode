package com.leetcode.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import org.junit.Test;
import com.leetcode.util.TreeNode;

public class SymmetricTreeIteration {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)  return true;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.empty()) {
            TreeNode right = stack.pop(), left = stack.pop();
            if (right == null && left == null) continue;
            if (right == null || left == null || right.val != left.val) return false;
            stack.push(right.left);
            stack.push(left.right);
            stack.push(right.right);
            stack.push(left.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll(), right = queue.poll();
            if (right == null && left == null)  continue;
            if (right == null || left == null || right.val != left.val) return false;
            queue.add(left.right);
            queue.add(right.left);
            queue.add(left.left);
            queue.add(right.right);
        }
        return true;
    }

    @Test
    public void test0() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        assertTrue(isSymmetric2(root));
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        assertFalse(isSymmetric2(root));
    }
}
