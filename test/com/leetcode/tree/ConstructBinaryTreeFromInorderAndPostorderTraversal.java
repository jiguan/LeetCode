package com.leetcode.tree;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import com.leetcode.util.TreeNode;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, inorderMap, postorder, 0,
                postorder.length - 1);
    }

    // The last element of postOrder must be the root
    // inOrder traverse is like a binary tree, the root is in the mid so we need both start and end
    // After we get the left subtree size, we can divide postOrder into left, right, root, both
    // start and end are needed
    private TreeNode build(int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inorderMap,
            int[] postorder, int postStart, int postEnd) {
        if (postStart > postEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inorderIndex = inorderMap.get(root.val);
        int leftSubSize = inorderIndex - inStart;

        root.left = build(inorder, inStart, inorderIndex - 1, inorderMap, postorder, postStart,
                postStart + leftSubSize - 1);
        root.right = build(inorder, inorderIndex + 1, inEnd, inorderMap, postorder,
                postStart + leftSubSize, postEnd - 1);
        return root;
    }

    public void test0() {
        int[] inorder = new int[] {4, 10, 3, 1, 7, 11, 8, 2};
        int[] postorder = new int[] {4, 1, 3, 10, 11, 8, 2, 7};
        TreeNode actual = buildTree(inorder, postorder);
        TreeNode expected =
                TreeNode.build(new Integer[] {7, 10, 2, 4, 3, 8, null, null, null, null, 1, 11});
        assertTrue(TreeNode.isSameTree(expected, actual));
    }
}
