package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;
import com.leetcode.util.TreeNode;

// The encoded string should be as compact as possible.
public class SerializeAndDeserializeBinaryTree {
    private static final String spliter = ",";
    private static final String NN = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals(NN)) return null;
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }

    @Test
    public void test0() {
        String str = "1,";
        String[] strs = str.split(",");
        assertEquals(1, strs.length);
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[] {1, 2, 3});
        assertEquals("1,2,#,#,3,#,#,", serialize(root));
        assertTrue(TreeNode.isSameTree(root, deserialize(serialize(root))));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.build(new Integer[] {1, 2, 3, 4});
        assertTrue(TreeNode.isSameTree(root, deserialize(serialize(root))));
    }
}
