package com.algorithm;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TreeNode;

public class MorrisTraversalForPreorder {
    // Preorder traversal without recursion and without stack
    public List<Integer> preorder(TreeNode node) {
        List<Integer> res = new LinkedList<>();
        while (node != null) {

            // If left child is null, print the current node data. Move to
            // right child.
            if (node.left == null) {
                res.add(node.val);
                node = node.right;
            } else {
                // Find inorder predecessor
                TreeNode current = node.left;
                while (current.right != null && current.right != node) {
                    current = current.right;
                }

                // If the right child of inorder predecessor already points to
                // this node
                if (current.right == node) {
                    current.right = null;
                    node = node.right;
                }

                // If right child doesn't point to this node, then print this
                // node and make right child point to this node
                else {
                    res.add(node.val);
                    current.right = node;
                    node = node.left;
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 5, 3, 4});
        PrettyPrint.print(preorder(root));
    }
}
