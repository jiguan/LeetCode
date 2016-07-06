package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class LowestCommonAncestorOfABinarySearchTree {
	public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
		int pDiff = root.val - p.val;
		int qDiff = root.val - q.val;
		if(pDiff * qDiff <= 0 ) return root;
		if(pDiff > 0) {
			return lowestCommonAncestor(root.left, p, q);
		} else {
			return lowestCommonAncestor(root.right, p, q);
		}
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(p.val > q.val) return lowestCommonAncestor(root, q, p);
		if(root.val > p.val && root.val < q.val) return root;
		return lowestCommonAncestor(root.val > q.val ? root.left : root.right, p, q);
	}
	
	@Test
	public void test0() {
		TreeNode root = new TreeNode(6);
		TreeNode node2 = new TreeNode(2);
		TreeNode node0 = new TreeNode(0);
		TreeNode node4 = new TreeNode(4);
		TreeNode node3 = new TreeNode(3);
		TreeNode node5 = new TreeNode(5);
		TreeNode node8 = new TreeNode(8);
		TreeNode node7 = new TreeNode(7);
		TreeNode node9 = new TreeNode(9);
		root.left = node2;
		root.right = node8;
		node2.left = node0;
		node2.right = node4;
		node4.left = node3;
		node4.right = node5;
		node8.left = node7;
		node8.right = node9;
		TreeNode result = lowestCommonAncestor(root, node2, node8);
		assertEquals(6, result.val);
	}
	
}
