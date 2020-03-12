package com.leetcode.tree;

import org.junit.Test;
import com.leetcode.util.Pair;
import com.leetcode.util.TreeNode;

// Oracle OCI, in-order: left - root - right
public class FlattenBinaryTreeToLinkedListInorder {
    public TreeNode transform(TreeNode root) {
        TreeNode newRoot = helper(root);
        while (newRoot.left != null) {
            newRoot = newRoot.left;
        }
        return newRoot;
    }

    // return root of in-order traverse result
    private TreeNode helper(TreeNode root) {
        if (root == null) return null;

        TreeNode left = helper(root.left);
        if (left != null) {
            while (left.right != null) {
                left = left.right;
            }
            left.right = root;
            root.left = left;
        } else {
            root.left = null;
        }

        TreeNode right = helper(root.right);
        if (right != null) {
            while (right.left != null) {
                right = right.left;
            }
            root.right = right;
            right.left = root;
        } else {
            root.right = null;
        }

        return root;
    }

    public TreeNode transformOpt(TreeNode root) {
        Pair<TreeNode, TreeNode> res = helperOptimze(root);
        return res.getKey();
    }

    // return head and tail node for in-order traverse
    private Pair<TreeNode, TreeNode> helperOptimze(TreeNode root) {
        if (root == null) return null;
        TreeNode head = root, tail = root;

        Pair<TreeNode, TreeNode> leftSub = helperOptimze(root.left);
        root.left = null;
        if (leftSub != null) {
            leftSub.getValue().right = root;
            root.left = leftSub.getValue().right;
            head = leftSub.getKey();
        }

        Pair<TreeNode, TreeNode> rightSub = helperOptimze(root.right);
        root.right = null;
        if (rightSub != null) {
            rightSub.getKey().left = root;
            root.right = rightSub.getKey();
            tail = rightSub.getValue();
        }

        Pair<TreeNode, TreeNode> res = new Pair<>(head, tail);
        return res;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] {5, 3, 6, 2, 1, null, 7});
        TreeNode expected = TreeNode.build(new Integer[] {2, 3, 1, 5, 6, 7});
        TreeNode.isSameTree(expected, transform(root));
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[] {5, 3, 6, 2, 1, null, 7});
        TreeNode expected = TreeNode.build(new Integer[] {2, 3, 1, 5, 6, 7});
        TreeNode.isSameTree(expected, transformOpt(root));
    }
}
