package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        int height = 1;
        Queue<TreeNode> queue = new LinkedList<>(), newQueue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (result.size() < height) {
                result.add(node.val);
            }
            if (node.right != null) newQueue.add(node.right);
            if (node.left != null) newQueue.add(node.left);
            if (queue.size() == 0) {
                queue = newQueue;
                newQueue = new LinkedList<>();
                height++;
            }
        }
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 1, result);
        return result;
    }

    private void dfs(TreeNode node, int height, List<Integer> result) {
        if (node == null) return;
        if (result.size() < height) {
            result.add(node.val);
        }
        dfs(node.right, height + 1, result);
        dfs(node.left, height + 1, result);
    }

    @Test
    public void test0() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 2, 4, 5));
        assertEquals(expected, rightSideView(root));
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.left = node6;
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 2, 6));
        assertEquals(expected, rightSideView(root));
    }
}
