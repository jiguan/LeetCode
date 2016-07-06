package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if(root==null) return 0;
        TreeNode leftNode = root;
        int leftLen = 0;
        while(leftNode.left!=null) {
            leftLen++;
            leftNode = leftNode.left;
        }
        int rightLen = 0;
        TreeNode rightNode = root;
        while(rightNode.right!=null) {
            rightLen++;
            rightNode = rightNode.right;
        }
        if(leftLen==rightLen) return (2 << leftLen) -1;
        else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    
    @Test
    public void test0() {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        assertEquals(3, countNodes(root));
    }
    
    @Test
    public void test1() {
        TreeNode root = new TreeNode(0);
        assertEquals(1, countNodes(root));
    }
}
