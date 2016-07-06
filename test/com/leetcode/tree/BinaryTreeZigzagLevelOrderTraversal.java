package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder0(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> current = new LinkedList<>(), next = new LinkedList<>();
        LinkedList<Integer> pool = new LinkedList<>();
        current.add(root);
        boolean flag = true;
        while (!current.isEmpty() || !next.isEmpty()) {
            if (current.isEmpty()) {
                current = next;
                next = new LinkedList<>();
                result.add(pool);
                pool = new LinkedList<>();
                flag = !flag;
            }
            TreeNode node = current.poll();
            if (flag) {
                pool.add(node.val);
            } else {
                pool.add(0, node.val);
            }
            if (node.left != null)
                next.add(node.left);
            if (node.right != null)
                next.add(node.right);

        }
        if (!pool.isEmpty()) {
            result.add(pool);
        }
        return result;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        helper(list, root, 0);
        return list;
    }

    private static void helper(List<List<Integer>> list, TreeNode root, int height) {
        if (root == null)
            return;
        if (height >= list.size()) {
            list.add(new ArrayList<>());
        }
        if (height%2==0) {
            list.get(height).add(root.val);
        } else {
            list.get(height).add(0, root.val);
        }
        helper(list, root.left, height + 1);
        helper(list, root.right, height + 1);
    }



    @Test
    public void test0() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;
        List<List<Integer>> result = zigzagLevelOrder(node1);
        for (List<Integer> r : result) {
            System.out.print("[");
            for (int i : r) {
                System.out.print(i + " ");
            }
            System.out.print("] ");
        }
    }

}
