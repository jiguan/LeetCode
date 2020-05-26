package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.leetcode.util.TreeNode;

/*
 * Binary Tree Longest Consecutive Sequence II
 * 
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 * 
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and
 * [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the
 * path can be in the child-Parent-child order, where not necessarily be parent-child order.
 * 
 * Example 1:
 * 
 * Input:
 * @formatter:off
 *     1
 *    / \
 *   2   3 
 * @formatter:on
 * Output: 2 Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * @formatter:off
 *     2
 *    / \
 *   1   3 
 * @formatter:on
 * Output: 3 Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 * 
 */
public class BinaryTreeLongestConsecutiveSequenceII {
    int maxval = 0;

    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return maxval;
    }

    public int[] longestPath(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int inr = 1, dcr = 1;
        if (root.left != null) {
            int[] left = longestPath(root.left);
            if (root.val == root.left.val + 1) {
                dcr = left[1] + 1;
            } else if (root.val == root.left.val - 1) {
                inr = left[0] + 1;
            }
        }
        if (root.right != null) {
            int[] right = longestPath(root.right);
            if (root.val == root.right.val + 1) {
                dcr = Math.max(dcr, right[1] + 1);
            } else if (root.val == root.right.val - 1) {
                inr = Math.max(inr, right[0] + 1);
            }
        }
        maxval = Math.max(maxval, dcr + inr - 1);
        return new int[] {inr, dcr};
    }

    @Test
    public void test0() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        assertEquals(3, longestConsecutive(root));
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[] {1, 2, 3, 4});
        assertEquals(2, longestConsecutive(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.build(new Integer[] {3, 1, null, null, 2});
        assertEquals(2, longestConsecutive(root));
    }
}
