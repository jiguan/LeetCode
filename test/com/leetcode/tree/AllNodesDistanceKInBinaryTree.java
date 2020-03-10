package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import com.leetcode.util.TreeNode;

public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Map<TreeNode, Integer> map = new HashMap<>();

        find(root, target, map);
        search(root, 0, K, res, map);

        return res;
    }

    // only traverse the subtree with target inside
    private void find(TreeNode root, TreeNode target, Map<TreeNode, Integer> map) {
        if (root == null) return;
        if (root == target) {
            map.put(root, 0);
            return;
        }
        find(root.left, target, map);
        if (map.containsKey(root.left)) {
            map.put(root, map.get(root.left) + 1);
            return;
        }
        find(root.right, target, map);
        if (map.containsKey(root.right)) {
            map.put(root, map.get(root.right) + 1);
            return;
        }
    }

    // get the distance from the root
    private void search(TreeNode node, int distance, int K, List<Integer> res,
            Map<TreeNode, Integer> map) {
        if (node == null) return;
        // if current node is in the subtree, distance is decreased
        if (map.containsKey(node)) distance = map.get(node);
        if (distance == K) res.add(node.val);
        search(node.left, distance + 1, K, res, map);
        search(node.right, distance + 1, K, res, map);
    }

    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[] {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode target = root.left;
        int K = 2;
        Set<Integer> expected = new HashSet<>(Arrays.asList(7, 4, 1));
        assertEquals(expected, new HashSet<>(distanceK(root, target, K)));
    }
}
