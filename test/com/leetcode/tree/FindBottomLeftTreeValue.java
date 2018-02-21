package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            // bfs from right to left, make sure the last one is left leaf
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{2, 1, 3});
        assertEquals(Integer.valueOf(1), Integer.valueOf(findBottomLeftValue(root)));
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 3, 4, null, 5, 6, null, null, 7});
        assertEquals(Integer.valueOf(7), Integer.valueOf(findBottomLeftValue(root)));
    }
}
