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
        if(root==null) return "null";
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node==null) {
                builder.append("null,");
            } else {
                builder.append(node.val+",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("null")) return null;
        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 0;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!arr[++index].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(arr[index]));  
                queue.add(node.left);
            }
            if(!arr[++index].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(arr[index]));
                queue.add(node.right);
            }
        }
        return root;
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
	
	@Test
    public void test3() {
        TreeNode head = new TreeNode(0);
        head.right = new TreeNode(1);
        
        assertEquals("0,null,1,null,null,", serialize(head));
        assertEquals("0,null,1,null,null,", serialize(deserialize("0,null,1,null,null,")));
    }
	
	
}
