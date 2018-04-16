package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] result = new List[n + 1];
        result[0] = new ArrayList<TreeNode>();
        if (n == 0) return result[0];

        result[0].add(null);
        // start from tree with len 1
        for (int len = 1; len <= n; ++len) {
            result[len] = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                for (TreeNode left : result[i]) {
                    for (TreeNode right : result[len - i - 1]) {
                        TreeNode node = new TreeNode(i + 1);
                        node.left = left;
                        // current root is node, right side is taken from
                        // previous tree but (root's val) larger than the root
                        node.right = clone(right, i + 1);
                        result[len].add(node);
                    }
                }
            }
        }
        return result[n];
    }

    private TreeNode clone(TreeNode n, int offset) {
        if (n == null) return null;
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    @Test
    public void test0() {
        int n = 3;
        List<TreeNode> result = generateTrees(n);
        assertEquals(Integer.valueOf(5), Integer.valueOf(result.size()));
    }
}
