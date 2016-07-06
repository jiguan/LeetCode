package com.leetcode.tree;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TreeLinkNode;

public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
        TreeLinkNode fakeHead = new TreeLinkNode(0);
        fakeHead.next = root;
        while(fakeHead.next!=null) {
            TreeLinkNode prev = fakeHead;
            TreeLinkNode node = fakeHead.next;
            fakeHead.next = null;
            //iterate current level
            while(node!=null) {
                //assign fakeHead.next to node.left
                if(node.left!=null) {
                    prev.next = node.left;
                    prev = prev.next;
                } 
                if(node.right!=null) {
                    prev.next = node.right;
                    prev = prev.next;
                }
                node = node.next;
            }
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
    
}
