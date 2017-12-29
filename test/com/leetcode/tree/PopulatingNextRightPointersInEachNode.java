package com.leetcode.tree;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TreeLinkNode;

public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
        TreeLinkNode levelHead = root;
        while(levelHead!=null) {
            TreeLinkNode node = levelHead;
            while(node!=null) {
                if(node.left!=null) node.left.next = node.right;
                // if node has right children and node is not the last in current level
                if(node.right!=null && node.next!=null) node.right.next = node.next.left;
                
                node = node.next;
            }
            levelHead = levelHead.left;
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
