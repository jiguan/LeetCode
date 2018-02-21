package com.leetcode.tree;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TreeLinkNode;

public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
        TreeLinkNode node = root;
        while (node != null) {
            TreeLinkNode head = null;
            TreeLinkNode pre = null;
            while (node != null) {
                if (node.left != null) {
                    if (pre != null) {
                        pre.next = node.left;
                    } else {
                        head = node.left;
                    }
                    pre = node.left;
                }

                if (node.right != null) {
                    if (pre != null) {
                        pre.next = node.right;
                    } else {
                        head = node.right;
                    }
                    pre = node.right;
                }
                node = node.next;
            }
            node = head;
        }
    }

    @Test
    public void test0() {
        TreeLinkNode root = new TreeLinkNode(0);
        root.left = new TreeLinkNode(1);
        root.right = new TreeLinkNode(2);
        root.left.left = new TreeLinkNode(3);
        root.right.left = new TreeLinkNode(4);
        root.right.right = new TreeLinkNode(5);
        connect(root);
        PrettyPrint.print(root);
    }

    @Test
    public void test1() {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.right.right = new TreeLinkNode(5);
        connect(root);
        PrettyPrint.print(root);
    }

    @Test
    public void test2() {
        TreeLinkNode root = new TreeLinkNode(0);
        connect(root);
        PrettyPrint.print(root);
    }

}
