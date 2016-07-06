package com.leetcode.tree;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TreeNode;

public class BSTToSortedLinkedList {

    private TreeNode convert(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return node;
        if (node.left != null) {
            TreeNode min = convert(node.left);
            TreeNode tmp = min;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
            tmp.right = node;
            node.left = null;
            node = min;
        }
        if (node.right != null) {
            node.right = convert(node.right);
        }
        return node;
    }

    @Test
    public void test0() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);
        TreeNode res = convert(root);

        PrettyPrint.print(res);

    }

}
