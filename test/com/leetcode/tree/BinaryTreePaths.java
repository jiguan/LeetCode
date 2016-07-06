package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreePaths {
	List<String> paths = new ArrayList<>();
	
	public List<String> binaryTreePaths0(TreeNode root) {
		if(root==null) return paths;
		dfs(root, new StringBuffer());
		return paths;
	}
	private void dfs(TreeNode node, StringBuffer path) {
		if(node.left==null && node.right==null) {
			path.append(node.val);
			paths.add(path.toString());
		} else {
			path.append(node.val);
			path.append("->");
			if(node.left!=null) {
				dfs(node.left, new StringBuffer(path));
			}
			if(node.right!=null) {
				dfs(node.right, path);
			}
		}
	}
	
	public List<String> binaryTreePaths(TreeNode root) {
		if(root==null) return paths;
		List<List<Integer>> result = dfs(root);
		for(List<Integer> path : result) {
			StringBuffer sb = new StringBuffer();
			for(Integer nodeNum : path) {
				sb.append(nodeNum+"->");
			}
			paths.add(sb.toString());
		}
		return paths;
	}
	
	public List<List<Integer>> dfs(TreeNode node) {
		List<List<Integer>> paths = new ArrayList<>();
		if(node.left==null && node.right==null) {
			List<Integer> path = new ArrayList<>();
			path.add(node.val);
			paths.add(path);
		} else {
			if(node.left!=null) {
				for(List<Integer> p : dfs(node.left)) {
					p.add(0, node.val);
					paths.add(p);
				}
			}
			if(node.right!=null) {
				for(List<Integer> p : dfs(node.right)) {
					p.add(0, node.val);
					paths.add(p);
				}
			}
		}
		return paths;
	}
	
	
	
	
	
	
	@Test
	public void test0() {
		TreeNode root = new TreeNode(0);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node3.left = node4;
		for(String path : binaryTreePaths(root)) {
			System.out.println(path);
		}
		
	}
}
