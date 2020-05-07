package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import com.leetcode.util.TreeNode;

/*
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * 
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union
 * of trees).
 * 
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 */
public class DeleteNodesAndReturnForest {
    // Space complexity: O(height)
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<Integer>();
        for (int del : to_delete) {
            set.add(del);
        }
        List<TreeNode> res = new LinkedList<>();
        delete(root, true, set, res);
        return res;
    }

    private TreeNode delete(TreeNode node, boolean isRoot, Set<Integer> to_delete,
            List<TreeNode> res) {
        if (node == null) return node;

        boolean deleted = to_delete.remove(node.val);
        if (isRoot && !deleted) {
            res.add(node);
        }

        // if we delete current node, its children will be root
        node.left = delete(node.left, deleted, to_delete, res);
        node.right = delete(node.right, deleted, to_delete, res);

        return deleted ? null : node;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        int[] to_delete = {3, 5};
        List<TreeNode> expected = Arrays.asList(TreeNode.build(new Integer[] {1, 2, null, 4}),
                new TreeNode(6), new TreeNode(7));
        assertEquals(expected, delNodes(root, to_delete));
    }
}
