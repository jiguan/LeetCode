package com.leetcode.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

/*
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the
 * node's key. The right subtree of a node contains only nodes with keys
 * greater than the node's key. Both the left and right subtrees must also
 * be binary search trees.
 * 
 */
public class ValidateBinarySearchTree {

	public boolean isValidBST(TreeNode root) {
		// The reason to choose Long min and max value is to avoid the situation
		// that node's value is int min/max value
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

	}

	public boolean isValidBST(TreeNode node, long min, long max) {
		if (node == null)
			return true;
		if (min >= node.val || node.val >= max) {
			return false;
		}
		return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
	}

	public boolean isValidInorder(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		TreeNode pre = null;
		
		while(node!=null || !stack.isEmpty()) {
			while(node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if(pre!=null && node.val <= pre.val) {
				return false;
			}
			pre = node;
			node = node.right;
		}
		
		return true;
	}
	
	@Test
	public void test0() {
		TreeNode root = TreeNode.build(new Integer[]{2,1,3});
		assertTrue(isValidInorder(root));
	}
	
	@Test
	public void test2() {
		TreeNode root = TreeNode.build(new Integer[]{1,2,3});
		assertFalse(isValidInorder(root));
	}
	
	@Test
	public void test1() {
		TreeNode root = TreeNode.build(new Integer[]{Integer.MAX_VALUE});
		assertTrue(isValidInorder(root));
	}

}
