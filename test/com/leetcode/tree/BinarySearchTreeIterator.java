package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class BinarySearchTreeIterator {
    Stack<TreeNode> stack = new Stack<>();
    
    public void BSTIterator(TreeNode root) {
        push(root);
    }
    
    private void push(TreeNode node) {
        while(node!=null) {
            stack.push(node);
            node = node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        push(node.right);
        return node.val;
    }
    
    @Test
    public void test0() {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        TreeNode root = TreeNode.build(nums);
        BSTIterator(root);
        for(int num : nums) {
            assertEquals(num, next());
        }
        assertFalse(hasNext());
    }
    
    @Test
    public void test1() {
        int[] nums = new int[]{1,2,3,4,5,6};
        TreeNode root = TreeNode.build(nums);
        BSTIterator(root);
        for(int num : nums) {
            assertEquals(num, next());
        }
        assertFalse(hasNext());
    }
}
