package com.leetcode.tree;

import com.leetcode.util.TreeNode;

/*
 * Count Univalue Subtrees
 * 
 * 
 * Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
 */
public class CountUnivalueSubtrees {
    int count;
    public int countUnivalSubtrees(TreeNode root) {
        count = 0;
        helper(root, Integer.MAX_VALUE);
        return count;
    }
    private boolean helper(TreeNode n, int v){
        if(n == null){
            return true;
        }
        boolean left = helper(n.left, n.val);
        boolean right = helper(n.right, n.val);
        if(left && right){
            count++;
            return n.val == v;
        }
        return false;
    }
}
