package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        traverse(root, 0, res);
        return res;
    }

    private void traverse(TreeNode node, int height, List<List<Integer>> res) {
        if (node == null) return;
        if (height >= res.size()) {
            res.add(0, new ArrayList<Integer>());
        }
        // height, height - 1, ... 1, 0
        res.get(res.size() - 1 - height).add(node.val);
        traverse(node.left, height + 1, res);
        traverse(node.right, height + 1, res);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 3, 4, 5});
        List<List<Integer>> expected = new LinkedList<>();
        expected.add(Arrays.asList(4, 5));
        expected.add(Arrays.asList(2, 3));
        expected.add(Arrays.asList(1));

        assertEquals(expected, levelOrderBottom(root));
    }
}
