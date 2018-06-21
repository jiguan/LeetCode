package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeSumQueryMutable {

    @Test
    public void test0() {
        int[] nums = new int[]{1, 3, 5};
        NumArray tree = new NumArray(nums);
        assertEquals(9, tree.sumRange(0, 2));
        tree.update(1, 2);
        assertEquals(8, tree.sumRange(0, 2));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{0, 9, 5, 7, 3};
        NumArray tree = new NumArray(nums);
        assertEquals(3, tree.sumRange(4, 4));
        assertEquals(15, tree.sumRange(2, 4));
        assertEquals(7, tree.sumRange(3, 3));
        tree.update(4, 5);
        tree.update(1, 7);
        tree.update(0, 8);
        assertEquals(12, tree.sumRange(1, 2));
        tree.update(1, 9);
        assertEquals(5, tree.sumRange(4, 4));
        tree.update(3, 4);
    }
}

class NumArray {
    SegmentNode root;

    public NumArray(int[] nums) {
        root = build(0, nums.length - 1, nums);
    }

    private SegmentNode build(int i, int j, int[] nums) {
        if (i > j) return null;
        SegmentNode node = new SegmentNode(i, j);

        if (i == j) {
            node.val = nums[i];
        } else {
            int mid = (j - i) / 2 + i;
            node.left = build(i, mid, nums);
            node.right = build(mid + 1, j, nums);
            node.val = node.left.val + node.right.val;
        }
        return node;
    }

    public void update(int i, int val) {
        update(i, val, root);
    }

    private void update(int i, int val, SegmentNode node) {
        int start = node.start, end = node.end;
        if (start == end) {
            node.val = val;
        } else {
            int mid = (end - start) / 2 + start;
            if (i <= mid) {
                update(i, val, node.left);
            } else {
                update(i, val, node.right);
            }
            node.val = node.left.val + node.right.val;
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(i, j, root);
    }

    private int sumRange(int i, int j, SegmentNode node) {
        if (i == node.start && j == node.end) {
            return node.val;
        }

        int mid = (node.end - node.start) / 2 + node.start;
        if (j <= mid) {
            return sumRange(i, j, node.left);
        } else if (mid < i) {
            return sumRange(i, j, node.right);
        } else {
            return sumRange(i, mid, node.left) + sumRange(mid + 1, j, node.right);
        }
    }

    class SegmentNode {
        SegmentNode left, right;
        int val;
        int start, end;

        SegmentNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
