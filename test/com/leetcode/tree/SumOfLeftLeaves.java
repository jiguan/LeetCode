package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

/** Find the sum of all left leaves in a given binary tree. **/
public class SumOfLeftLeaves {
	public int sumOfLeftLeaves(TreeNode root) {
		int res = 0;
		if (root == null) return res;
		if (root.left != null && root.left.left == null && root.left.right == null) {
			res += root.left.val;
		} else {
			res += sumOfLeftLeaves(root.left);
		}
		res += sumOfLeftLeaves(root.right);
		return res;
	}

	@Test
	public void test0() {
		TreeNode root = TreeNode.build(new Integer[] { 3, 9, 20, null, null, 15, 7 });
		assertEquals(24, sumOfLeftLeaves(root));
	}

	@Test
	public void test1() {
		TreeNode root = TreeNode.build(new Integer[] { 3, 9, 20 });
		assertEquals(9, sumOfLeftLeaves(root));
	}

	@Test
	public void test2() {
		TreeNode root = TreeNode.build(new Integer[] { 1, 2, 3, 4 });
		assertEquals(4, sumOfLeftLeaves(root));
	}
}
