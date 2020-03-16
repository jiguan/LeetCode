package com.leetcode.tree;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.leetcode.util.TreeNode;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // value - index
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = build(preorder, 0, inorder, 0, inorder.length - 1, inorderMap);
        return root;
    }

    // since in preOrder, the first is always the root's value, we just need preStart
    // inOrder traverse is like a binary search, the root is in the mid, we need both start and end
    private TreeNode build(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd || preStart >= preorder.length) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inorderIndex = inorderMap.get(preorder[preStart]);

        int leftSubSize = inorderIndex - inStart;
        root.left = build(preorder, preStart + 1, inorder, inStart, inorderIndex - 1, inorderMap);
        root.right = build(preorder, preStart + leftSubSize + 1, inorder, inorderIndex + 1, inEnd,
                inorderMap);

        return root;
    }

    @Test
    public void test0() {
        int[] preorder = new int[] {7, 10, 4, 3, 1, 2, 8, 11};
        int[] inorder = new int[] {4, 10, 3, 1, 7, 11, 8, 2};
        TreeNode actual = buildTree(preorder, inorder);
        TreeNode expected =
                TreeNode.build(new Integer[] {7, 10, 2, 4, 3, 8, null, null, null, null, 1, 11});
        assertTrue(TreeNode.isSameTree(expected, actual));
    }
}
