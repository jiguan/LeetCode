package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class SerializeAndDeserializeBinaryTree2 {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root==null) return "(n)";
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		sb.append(root.val);
		sb.append(serialize(root.left));
		sb.append(serialize(root.right));
		sb.append(")");
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if(data.equals("(n)")) return null;
		char[] arr = data.toCharArray();
		int middleIndex = findBoundary(arr);
		for(int i=1;i<arr.length;i++) {
			if(arr[i]=='(') {
				TreeNode head = new TreeNode(Integer.valueOf(data.substring(1, i)));
				TreeNode left = deserialize(data.substring(i, middleIndex));
				TreeNode right = deserialize(data.substring(middleIndex, data.length()-1));
				head.left = left;
				head.right = right;
				return head;
			}
		}
		return null;
	}
	
	//return the index, data is like (1(n)(n))
	private int findBoundary(char[] arr) {
		int deep = -1;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]=='(') deep++;
			if(arr[i]==')') {
				deep--;
				if(deep==0) return i+1;
			}
		}
		return -1;
	}
	
	@Test
	public void test1() {
		TreeNode head = new TreeNode(0);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		head.left = left;
		head.right = right;
		assertEquals("(0(1(n)(n))(2(n)(n)))", serialize(head));
		assertEquals("(0(1(n)(n))(2(n)(n)))", serialize(deserialize("(0(1(n)(n))(2(n)(n)))")));
	}
	
	@Test
	public void test2() {
		TreeNode head = new TreeNode(0);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		
		head.left = node1;
		head.right = node2;
		node1.left = node3;
		node2.right = node4;
		node3.left = node5;
		
		assertEquals("(0(1(3(5(n)(n))(n))(n))(2(n)(4(n)(n))))", serialize(head));
		assertEquals("(0(1(3(5(n)(n))(n))(n))(2(n)(4(n)(n))))", serialize(deserialize("(0(1(3(5(n)(n))(n))(n))(2(n)(4(n)(n))))")));
	}
	
	
}
