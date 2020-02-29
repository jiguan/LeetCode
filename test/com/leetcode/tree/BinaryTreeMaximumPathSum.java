package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreeMaximumPathSum {
	private int res = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode node) {
		if (node == null)
			return 0;
		sum(node);
		return res;
	}

	private int sum(TreeNode node) {
		if (node == null)
			return 0;
		int val = node.val;
		int left = Math.max(0, sum(node.left));
		int right = Math.max(0, sum(node.right));
		res = Math.max(val + left + right, res);

		// a path is defined as any sequence of nodes from some starting node to any
		// node in the tree along the parent-child connections
		return Math.max(left, right) + val;
	}

	@Test
	public void test0() {
		res = Integer.MIN_VALUE;
		TreeNode root = TreeNode.build(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1 });
		int expected = 48;
		assertEquals(expected, maxPathSum(root));
	}

	@Test
	public void test1() {
		res = Integer.MIN_VALUE;
		TreeNode root = null;
		int expected = 0;
		assertEquals(expected, maxPathSum(root));
	}

	@Test
	public void test2() {
		res = Integer.MIN_VALUE;
		TreeNode root = TreeNode.build(new Integer[] { 2, -1 });
		int expected = 2;
		assertEquals(expected, maxPathSum(root));
	}
}
