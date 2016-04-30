package practice.first.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Stack;

import org.junit.Test;

import practice.first.util.TreeNode;

public class PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root==null) return false;
		sum -= root.val;
		if(sum==0&&root.left==null&&root.right==null) return true;
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}
	
	@Test
	public void test0() {
		TreeNode root = new TreeNode(5);
		TreeNode node1 = new TreeNode(4), node2 = new TreeNode(8), 
				node3 = new TreeNode(11), node4 = new TreeNode(13),
				node5 = new TreeNode(4), node6 = new TreeNode(7),
				node7 = new TreeNode(2), node8 = new TreeNode(1);
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node5.right = node8;
		assertTrue(hasPathSum(root, 22));
	}
	
	@Test
	public void test1() {
		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		root.left = node1;
		assertFalse(hasPathSum(root, 1));
	}
	
	@Test
	public void test2() {
		TreeNode root = new TreeNode(-2);
		TreeNode node1 = new TreeNode(-3);
		root.right = node1;
		assertTrue(hasPathSum(root, -5));
	}
}
