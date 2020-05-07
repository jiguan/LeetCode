package com.leetcode.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left, right, parent;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TreeNode) {
            TreeNode tmp = (TreeNode) obj;
            return this.val == tmp.val && (this.left != null ? this.left.equals(tmp.left) : true)
                    && (this.right != null ? this.right.equals(tmp.right) : true);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right, parent);
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
                node.left.parent = node;
                queue.add(node.left);
            }
            if (++i < nums.length && nums[i] != null) {
                node.right = new TreeNode(nums[i]);
                node.right.parent = node;
                queue.add(node.right);
            }
        }
        return root;
    }

    public static boolean isSameTree(TreeNode expect, TreeNode actual) {
        if (expect == null && actual == null) return true;
        if (expect == null || actual == null) return false;
        return expect.val == actual.val && isSameTree(expect.left, actual.left)
                && isSameTree(expect.right, actual.right);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node == null ? "null" : String.valueOf(node.val));
            if (node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return String.join(",", res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        int i = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = parse(strs[0]);
        queue.add(root);
        int len = strs.length;

        while (i < len) {
            TreeNode node = queue.poll();
            if (++i < len && !strs[i].equals("null")) {
                node.left = parse(strs[i]);
                queue.add(node.left);
            }
            if (++i < len && !strs[i].equals("null")) {
                node.right = parse(strs[i]);
                queue.add(node.right);
            }
        }
        return root;
    }

    private TreeNode parse(String data) {
        if (data == null || data.isEmpty() || data.equals("null")) return null;
        return new TreeNode(Integer.valueOf(data));
    }
}
