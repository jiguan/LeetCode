package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class CountOfSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<Integer>();
        if (nums == null || nums.length == 0) return res;
        // last element as the root, from the tail to head to build the tree
        Node root = new Node(nums[nums.length - 1]);
        for (int i = nums.length - 1; i >= 0; i--) {
            res.add(0, insert(root, nums[i]));
        }
        return res;
    }

    // return how many elements smaller than current num
    private int insert(Node node, int num) {
        int total = 0;
        while (node.val != num) {
            if (node.val > num) {
                // one more smaller val
                node.leftSize++;
                if (node.left == null) node.left = new Node(num);
                node = node.left;
            } else {
                // node.val < num
                total += node.leftSize + node.count;
                if (node.right == null) node.right = new Node(num);
                node = node.right;
            }
        }
        node.count++;
        return total + node.leftSize;
    }

    @Test
    public void test0() {
        int[] nums = {5, 2, 6, 1};
        List<Integer> res = Arrays.asList(2, 1, 1, 0);
        assertEquals(res, countSmaller(nums));
    }

    @Test
    public void test2() {
        int[] nums = {100, 33, 67, 90, 100, 41};
        List<Integer> res = Arrays.asList(4, 0, 1, 1, 1, 0);
        assertEquals(res, countSmaller(nums));
    }

    @Test
    public void test1() {
        int[] nums = {26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69,
                81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41};
        List<Integer> res = Arrays.asList(10, 27, 10, 35, 12, 22, 28, 8, 19, 2, 12, 2, 9, 6, 12, 5,
                17, 9, 19, 12, 14, 6, 12, 5, 12, 3, 0, 10, 0, 7, 8, 4, 0, 0, 4, 3, 2, 0, 1, 0);
        assertEquals(res, countSmaller(nums));
    }
}


class Node {
    int val;
    // number of left subtree
    int leftSize = 0;
    // how many time this val occurs
    int count = 0;
    Node left, right;

    public Node(int val) {
        this.val = val;
    }
}
