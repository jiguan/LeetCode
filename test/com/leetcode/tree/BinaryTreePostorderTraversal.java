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
    public List<Integer> postorderTraversal0(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            // push left first to the stack so that it will be retrieved at last
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        Collections.reverse(res);
        return res;
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(0, node.val);
            if(node.left!=null) {
                stack.push(node.left);
            }
            if(node.right!=null) {
                stack.push(node.right);
            }
        }
        return res;
    }
    
    @Test
    public void test0() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        List<Integer> expected = Arrays.asList(2, 3, 1);
        assertEquals(expected, postorderTraversal(root));
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

        List<Integer> expected = Arrays.asList(3, 4, 2, 6, 7, 5, 1);
        assertEquals(expected, postorderTraversal(root));
    }
}
