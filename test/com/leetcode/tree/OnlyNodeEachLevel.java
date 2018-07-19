package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class OnlyNodeEachLevel {
    public List<Integer> traverse(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size == 1) {
                res.add(queue.peek().val);
            }
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{7, 6, 8, 5, null, 9, null, null, null, 10});
        Set<Integer> expected = new HashSet<>(Arrays.asList(7, 10));
        assertEquals(expected, new HashSet<>(traverse(root)));
    }
}
