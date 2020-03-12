package com.leetcode.tree;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.leetcode.util.Pair;
import com.leetcode.util.TreeNode;

public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        helper(root);
    }

    private Pair<TreeNode, TreeNode> helper(TreeNode root) {
        if (root == null) return null;
        Pair<TreeNode, TreeNode> leftSub = helper(root.left);
        Pair<TreeNode, TreeNode> rightSub = helper(root.right);

        root.left = null;
        TreeNode tail = root;

        if (leftSub != null) {
            root.right = leftSub.getKey();
            tail = leftSub.getValue();
        }

        if (rightSub != null) {
            tail.right = rightSub.getKey();
            tail = rightSub.getValue();
        }
        Pair<TreeNode, TreeNode> res = new Pair<>(root, tail);
        return res;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] {1, 2, 5, 3, 4, null, 6});
        TreeNode expected =
                TreeNode.build(new Integer[] {1, null, 2, null, 3, null, 4, null, 5, null, 6});
        flatten(root);
        assertTrue(TreeNode.isSameTree(expected, root));
    }
}
