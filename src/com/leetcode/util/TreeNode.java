package com.leetcode.util;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return String.format("TreeNode(%d)", val);
	}

	public static TreeNode build(Integer[] nums) {
		TreeNode root = new TreeNode(nums[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int i = 0;
		while (i < nums.length) {
			TreeNode node = queue.poll();
			if (++i < nums.length && nums[i] != null) {
				node.left = new TreeNode(nums[i]);
				queue.add(node.left);
			}
			if (++i < nums.length && nums[i] != null) {
				node.right = new TreeNode(nums[i]);
				queue.add(node.right);
			}
		}
		return root;
	}

	public static boolean isSameTree(TreeNode expect, TreeNode actual) {
		if (expect == null && actual == null) return true;
		if (expect == null || actual == null) return false;
		return expect.val == actual.val && isSameTree(expect.left, actual.left) && isSameTree(expect.right, actual.right);
	}

}
