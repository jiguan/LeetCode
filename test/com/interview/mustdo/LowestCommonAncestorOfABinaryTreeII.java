package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class LowestCommonAncestorOfABinaryTreeII {
    public TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
        int h1 = getHeight(p);
        int h2 = getHeight(q);

        // make sure p is deeper tree
        if (h1 < h2) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }

        int diff = Math.abs(h1 - h2);
        for (int i = 0; i < diff; ++i) {
            p = p.parent;
        }

        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }

    private int getHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            node = node.parent;
            height++;
        }
        return height;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{0,1,2,3,4,5,6,7,8});
        assertEquals(0, lowestCommonAncestor(root.left.left.right, root.right.left).val);
    }
}
