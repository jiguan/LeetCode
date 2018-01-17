package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return build(0, inorder.length - 1, inorder, postorder.length - 1, postorder, inMap);
    }

    private TreeNode build(int inStart, int inEnd, int[] inorder, int postEnd, int[] postorder,
            Map<Integer, Integer> inMap) {
        if (inStart > inEnd || postEnd < 0) return null;
        // Last element of postorder is the root
        TreeNode root = new TreeNode(postorder[postEnd]);
        // Get the index in inorder and divide inorder array into two subtrees.
        int rootInorderIndex = inMap.get(root.val);
        int rightSubtreeSize = inEnd - rootInorderIndex; // right tree length

        // Remember remove root index/size
        TreeNode right = build(rootInorderIndex + 1, inEnd, inorder, postEnd - 1, postorder, inMap);
        TreeNode left = build(inStart, rootInorderIndex - 1, inorder, postEnd - rightSubtreeSize - 1, postorder, inMap);
        root.left = left;
        root.right = right;
        return root;
    }

    @Test
    public void test0() {
        int[] inorder = new int[] { 4, 10, 3, 1, 7, 11, 8, 2 };
        int[] postorder = new int[] { 4, 1, 3, 10, 11, 8, 2, 7 };
        TreeNode actual = buildTree(inorder, postorder);
        TreeNode expected = TreeNode.build(new Integer[] { 7, 10, 2, 4, 3, 8, null, null, null, null, 1, 11 });
        assertTrue(TreeNode.isSameTree(expected, actual));
    }
}
