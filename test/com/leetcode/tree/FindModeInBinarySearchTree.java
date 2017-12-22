package com.leetcode.tree;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class FindModeInBinarySearchTree {
    Integer prev = null;
    int count = 1;
    int max = 0;

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        List<Integer> list = new ArrayList<>();
        traverse(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void traverse(TreeNode node, List<Integer> list) {
        if (node == null) return;
        traverse(node.left, list);
        if (prev != null) {
            if (node.val == prev) {
                ++count;
            } else {
                count = 1;
            }
        }
        if (count > max) {
            max = count;
            list.clear();
            list.add(node.val);
        } else if (count == max) {
            list.add(node.val);
        }
        prev = node.val;
        traverse(node.right, list);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{1, null, 2, 2});
        assertArrayEquals(new int[]{2}, findMode(root));
    }

}
