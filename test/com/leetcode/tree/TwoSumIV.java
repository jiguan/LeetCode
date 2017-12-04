package com.leetcode.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class TwoSumIV {
	/*
	 * The idea is to use binary search method. For each node, we check if k - node.val exists in this BST.
     * Time Complexity: O(nlog(n)), Space Complexity: O(h). h is the height of the tree, which is log(n) at best case, and n at worst case.
	 */
	public boolean findTarget(TreeNode root, int k) {
		return dfs(root, root, k);
	}
	
	private boolean dfs(TreeNode root, TreeNode cur, int value) {
		if(cur == null) return false;
		return search(root, cur, value - cur.val) || 
				dfs(root, cur.left, value) || 
				dfs(root, cur.right, value);
	}
	
	private boolean search(TreeNode root, TreeNode cur, int value) {
		if(root == null) return false;
		return (root.val == value) && (root != cur) || 
				(root.val < value) && search(root.right, cur, value) ||
				(root.val > value) && search(root.left, cur, value);
	}
	
	@Test
	public void test0() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(7);

		assertTrue(findTarget(root, 9));
	}

	@Test
	public void test1() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(7);

		assertFalse(findTarget(root, 28));
	}
}
