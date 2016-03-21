package practice.first.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import practice.first.util.TreeNode;

public class KthSmallestElementInABST {
	private int counter = 0;
	public int kthSmallest(TreeNode root, int k) {
		return dfs(root, k).val;
	}
	
	private TreeNode dfs(TreeNode node, int k) {
		if(node == null) {
			return null;
		}
		TreeNode tmp = dfs(node.left, k);
		if(++counter == k) return node;
		if(tmp==null) {
			tmp = dfs(node.right, k);
		}
		return tmp;
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
		assertEquals(2, kthSmallest(root, 2));
	}
	
	@Test
	public void test1() {
		TreeNode root = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		root.right = right;
		assertEquals(2, kthSmallest(root, 2));
	}
	
	@Test
	public void test2() {
		TreeNode root = new TreeNode(2);
		TreeNode left = new TreeNode(1);
		root.left = left;
		assertEquals(1, kthSmallest(root, 1));
	}
}
