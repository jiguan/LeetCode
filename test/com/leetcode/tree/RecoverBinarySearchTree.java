package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class RecoverBinarySearchTree {
	public void recoverTree(TreeNode root) {
		inorder(root);
		int tmp = first.val;
		first.val = second.val;
		second.val = tmp;
	}

	TreeNode prev = null, first = null, second = null;

	private void inorder(TreeNode node) {
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
					
			node = stack.pop();
			if(prev!=null && prev.val >= node.val) {
				if(first==null) first = prev;
				second = node;
			}
			prev = node;
			node = node.right;
		}
	}

	@Test
	public void test0() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(4);
		recoverTree(root);

		assertEquals(4, root.left.val);
		assertEquals(6, root.right.left.val);
	}
}
