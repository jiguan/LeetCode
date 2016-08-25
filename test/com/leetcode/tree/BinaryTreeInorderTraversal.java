package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreePostorderTraversal {
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;

		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			res.add(node.val);
			node = node.right;
		}

		return res;
	}

	@Test
	public void test() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		List<Integer> expected = Arrays.asList(4, 2, 1, 3, 5);
		assertEquals(expected, postorderTraversal(root));
	}
}
