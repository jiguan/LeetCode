package com.leetcode.tree;

import com.leetcode.util.TreeNode;

/*
 * Binary Tree Longest Consecutive Sequence
 * 
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * 
 * The path refers to any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The longest consecutive path need to be from parent to child
 * (cannot be the reverse).
 * 
 * Example 1:
 * 
 * Input:
 * 
 * @formatter:off
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * @formatter:on
 * Output: 3
 * 
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * Example 2:
 * 
 * Input:
 * @formatter:off
 *    2
 *     \
 *      3
 *     / 
 *    2    
 *   / 
 *  1
 * @formatter:on
 * 
 * Output: 2
 * 
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */

public class BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return DFS(root, root.val + 1, 1, 1);
    }

    private int DFS(TreeNode node, int target, int curr, int max) {
        if (node == null) {
            return max;
        }
        if (node.val == target) {
            curr++;
            max = Math.max(max, curr);
        } else {
            curr = 1;
        }
        return Math.max(DFS(node.left, node.val + 1, curr, max),
                DFS(node.right, node.val + 1, curr, max));
    }
}
