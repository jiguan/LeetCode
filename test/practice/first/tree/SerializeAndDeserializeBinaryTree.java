package practice.first.tree;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import practice.first.util.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class SerializeAndDeserializeBinaryTree {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuffer buffer = new StringBuffer();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(queue.size()!=0) {
			TreeNode node = queue.poll();
			if(node!=null) {
				buffer.append(node.val);
				buffer.append(",");
				TreeNode left = node.left;
				TreeNode right = node.right;
				queue.add(left);
				queue.add(right);
			} else {
				buffer.append("null,");
			}
		}
		return buffer.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] arr = data.split(",");
		TreeNode head = parse(arr[0]);
		TreeNode prev = head;
		boolean leftNode = true;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		for(int i=1; i<arr.length;i++) {
			TreeNode node = parse(arr[i]);
			if(node!=null) {
				queue.add(node);
			}
			if(leftNode) {
				prev.left = node;
			} else {
				prev.right = node;
				prev = queue.poll();
			}
			leftNode = !leftNode;
		}
		return head;
	}
	
	private TreeNode parse(String s) {
		if(s.equals("null")) return null;
		else return new TreeNode(Integer.parseInt(s));
	}
	
	@Test
	public void test1() {
		TreeNode head = new TreeNode(0);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		head.left = left;
		head.right = right;
		assertEquals("0,1,2,null,null,null,null,", serialize(head));
		assertEquals("0,1,2,null,null,null,null,", serialize(deserialize("0,1,2,null,null,null,null,")));
	}
	
	@Test
	public void test2() {
		TreeNode head = new TreeNode(0);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		
		head.left = node1;
		head.right = node2;
		node2.left = node3;
		
		assertEquals("0,1,2,null,null,3,null,null,null,", serialize(head));
		assertEquals("0,1,2,null,null,3,null,null,null,", serialize(deserialize("0,1,2,null,null,3,null,null,null,")));
	}
	
	
}
