package com.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.leetcode.util.Arrays;
import com.leetcode.util.TreeNode;

public class MostFrequentSubtreeSum {
    Map<Integer, Integer> map = new HashMap<>();
    int max = Integer.MIN_VALUE;
    public int[] findFrequentTreeSum(TreeNode root) {
        traverse(root);
        List<Integer> res = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                res.add(entry.getKey());
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    private int traverse(TreeNode node) {
        if (node == null) return 0;
        int sum = node.val + traverse(node.left) + traverse(node.right);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        max = Math.max(max, map.get(sum));
        return sum;
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{5, 2, -3});
        int[] expected = new int[]{2, -3, 4};
        Arrays.assertEquals(expected, findFrequentTreeSum(root));
    }
}
