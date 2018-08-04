package com.hackerank.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.TreeNode;

//@formatter:off
/*
 *     1
 *  2     3
 *   (4,5)  6
 *   
 *   return {{2}, {1, 4, 5}, {3}, {6}
 */
//@formatter:on
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, List<Integer>> map = new HashMap<>();
        traverse(root, 0, map);
        for (int i = -map.size(); i < map.size(); ++i) {
            if (map.containsKey(i)) {
                res.add(map.get(i));
            }
        }
        return res;
    }

    private void traverse(TreeNode node, int level, Map<Integer, List<Integer>> map) {
        if (node == null) return;
        if (!map.containsKey(level)) {
            map.put(level, new LinkedList<>());
        }
        map.get(level).add(node.val);
        traverse(node.left, level - 1, map);
        traverse(node.right, level + 1, map);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{0, 1, 2, 3, 4, 5, 6});
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3));
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(0, 4, 5));
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(6));
        assertEquals(expected, verticalOrder(root));
    }
}
