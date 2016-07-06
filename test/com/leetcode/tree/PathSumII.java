package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class PathSumII {
	public List<List<Integer>> pathSum0(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null)
			return result;
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		List<Integer> path = new ArrayList<>();
		Set<TreeNode> visited = new HashSet<>();
		while (!stack.isEmpty()) {
			TreeNode node = stack.peek();
			if (visited.add(node)) {
				path.add(node.val);
				sum -= node.val;
			}

			if ((node.left == null || visited.contains(node.left))
					&& (node.right == null || visited.contains(node.right))) {
				if (sum == 0 && node.left == null && node.right == null) {
					result.add(new ArrayList<>(path));
				}
				stack.pop();
				sum += node.val;
				path.remove(path.size() - 1);
				continue;
			}
			if (node.left != null && !visited.contains(node.left)) {
				stack.add(node.left);
			}
			if (node.right != null && !visited.contains(node.right)) {
				stack.add(node.right);
			}
		}
		return result;
	}

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> paths = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		findPath(root, sum, paths, tmp);
		return paths;
	}

	private void findPath(TreeNode node, int sum, List<List<Integer>> paths, List<Integer> tmp) {
		if(node==null) return;
		sum -= node.val;
		tmp.add(node.val);
		if (node.left == null && node.right == null) {
			if (sum == 0) {
				paths.add(new ArrayList<>(tmp));
			}
		} else {
			findPath(node.left, sum, paths, tmp);
			findPath(node.right, sum, paths, tmp);
		}
		tmp.remove(tmp.size()-1);
	}

	private void prettyPrint(List<Integer> list) {
		System.out.print("[");
		for (int num : list) {
			System.out.print(num + ", ");
		}
		System.out.println("]");
	}

	@Test
	public void test0() {
		TreeNode root = new TreeNode(5), node1 = new TreeNode(1), node2 = new TreeNode(2), node3 = new TreeNode(1),
				node4 = new TreeNode(2);
		root.left = node1;
		node1.left = node2;
		root.right = node3;
		node3.right = node4;
		List<List<Integer>> result = pathSum(root, 8);
		for (List<Integer> list : result) {
			prettyPrint(list);
		}
	}

	@Test
	public void test1() {
		TreeNode root = new TreeNode(1), node1 = new TreeNode(-2), node2 = new TreeNode(1), node3 = new TreeNode(3),
				node4 = new TreeNode(-1), node5 = new TreeNode(-3), node6 = new TreeNode(-2);
		root.left = node1;
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		root.right = node5;
		node5.right = node6;
		List<List<Integer>> result = pathSum(root, 1);
		assertTrue(result.isEmpty());
	}
}
