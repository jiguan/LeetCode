package com.geeksforgeeks.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InorderSuccessorInBST {
	// http://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/

	public TreeNode insert(TreeNode node, int val) {
		if (node == null) {
			return new TreeNode(val);
		} else {
			if(node.val > val) {
				node.left = insert(node.left, val);
				node.left.parent = node;
			} else {
				node.right = insert(node.right, val);
				node.right.parent = node;
			}
			return node;
		}
	}

	public TreeNode inOrderSuccessor(TreeNode n) {
		if (n.right != null) {
			n = n.right;
			while (n.left != null) {
				n = n.left;
			}
			return n;
		}
		// n has no right
		TreeNode parent = n.parent;
		while (parent != null && n == parent.right) {
			n = parent;
			parent = n.parent;
		}
		return parent;
	}

	class TreeNode {
		int val;
		TreeNode left, right, parent;

		TreeNode(int val) {
			this.val = val;
			left = right = parent = null;
		}
		
		@Override
		public String toString() {
			return String.format("TreeNode(%d)", val);
		}
	}
	
	@Test
	public void test0() {
	    TreeNode root = insert(null, 20);
        root = insert(root,8);
        root = insert(root,22);
        root = insert(root,4);
        root = insert(root,12);
        root = insert(root,10);
        root = insert(root,14);
        //test node just smaller than the root
        TreeNode node = root.left;
        while(node.right!=null) {
        	node = node.right;
        }
        assertEquals(root, inOrderSuccessor(node));
	}

}
