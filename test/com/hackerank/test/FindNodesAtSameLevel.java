package com.hackerank.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TreeNode;

public class FindNodesAtSameLevel {
    public List<TreeNode> findNodes(TreeNode root, TreeNode target) {
        List<TreeNode> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isFound = false;
        while (!queue.isEmpty() && isFound == false) {
            int size = queue.size();
            res = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == target) {
                    isFound = true;
                }
                res.add(node);
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
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        TreeNode target = root.right.right;
        List<TreeNode> actual = findNodes(root, target);
        PrettyPrint.print(actual);
    }
}
