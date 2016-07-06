package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class LowestCommonAncestorOfABinaryTree {
	TreeNode parent = null;
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		search(root, p, q);
		return parent;
	}
	
	private int search(TreeNode node, TreeNode p, TreeNode q) {
		if(node==null) return 0;
		int match = 0;
		if(node.val==p.val||node.val==q.val) {
			match++;
		}
		int left = search(node.left, p, q);
		int right = search(node.right, p, q);
		match = match + left + right;
		if(match >= 2 && parent ==null) {
			parent = node;
		}
		return match;
	}
	
	/*
	 *  _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
	 */
	
	
	@Test
	public void test0() {
		TreeNode root = new TreeNode(3);
		TreeNode node5 = new TreeNode(5), 
				 node1 = new TreeNode(1),
				 node6 = new TreeNode(6),
				 node2 = new TreeNode(2),
				 node0 = new TreeNode(0),
				 node8 = new TreeNode(8),
				 node7 = new TreeNode(7),
				 node4 = new TreeNode(4);				 
		root.left = node5;
		root.right = node1;
		node5.left = node6;
		node5.right = node2;
		node2.left = node7;
		node2.right = node4;
		node1.left = node0;
		node1.right = node8;
		
		assertEquals(node5, lowestCommonAncestor(root, node6, node4));
		parent = null;
		assertEquals(node2, lowestCommonAncestor(root, node2, node4));
		parent = null;
		assertEquals(root, lowestCommonAncestor(root, node6, node8));
		parent = null;
	}
	
}
