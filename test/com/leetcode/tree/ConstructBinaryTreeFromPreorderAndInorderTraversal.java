package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    // value - index
    Map<Integer, Integer> inMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; ++i) {
            inMap.put(inorder[i], i);
        }
        return build(0, inorder.length - 1, inorder, 0, preorder);
    }

    private TreeNode build(int inStart, int inEnd, int[] inorder, int preStart, int[] preorder) {
        if (inStart > inEnd || preStart < 0) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int index = inMap.get(root.val);
        int leftSubtreeSize = index - inStart;
        root.left = build(inStart, index - 1, inorder, preStart + 1, preorder);
        root.right = build(index + 1, inEnd, inorder, preStart + leftSubtreeSize + 1, preorder);

        return root;
    }

    @Test
    public void test0() {
        int[] preorder = new int[]{7, 10, 4, 3, 1, 2, 8, 11};
        int[] inorder = new int[]{4, 10, 3, 1, 7, 11, 8, 2};
        TreeNode actual = buildTree(preorder, inorder);
        TreeNode expected = TreeNode.build(new Integer[]{7, 10, 2, 4, 3, 8, null, null, null, null, 1, 11});
        assertTrue(TreeNode.isSameTree(expected, actual));
    }
}
