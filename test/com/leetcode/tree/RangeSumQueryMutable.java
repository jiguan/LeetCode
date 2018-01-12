package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeSumQueryMutable {
    SegmentNode root;

    public RangeSumQueryMutable() {
    }

    public void set(int[] nums) {
        root = build(0, nums.length - 1, nums);
    }

    // public RangeSumQueryMutable(int[] nums) {
    // root = build(0, nums.length - 1, nums);
    // }

    private SegmentNode build(int start, int end, int[] nums) {
        if (start > end) return null;
        SegmentNode node = new SegmentNode(start, end);

        if (start == end) {
            node.val = nums[start];
        } else {
            int mid = (start + end) >> 1;
            node.left = build(start, mid, nums);
            node.right = build(mid + 1, end, nums);
            node.val = node.left.val + node.right.val;
        }
        return node;
    }

    public void update(int i, int val) {
        update(i, val, root);
    }

    private void update(int i, int val, SegmentNode node) {
        if (node.start == node.end) {
            node.val = val;
        } else {
            int mid = (node.start + node.end) >> 1;
            // [0, 4] => [0, 2], [3, 4]
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
        } else {
            int mid = (node.start + node.end) >> 1;
            if (j <= mid) {
                return sumRange(i, j, node.left);
            } else if (mid < i) {
                return sumRange(i, j, node.right);
            } else {
                return sumRange(i, mid, node.left) + sumRange(mid + 1, j, node.right);
            }
        }
    }

    private class SegmentNode {
        int val;
        int start, end;
        SegmentNode left, right;
        public SegmentNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 3, 5};
        set(nums);
        assertEquals(9, sumRange(0, 2));
        update(1, 2);
        assertEquals(8, sumRange(0, 2));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{0, 9, 5, 7, 3};
        set(nums);
        assertEquals(3, sumRange(4, 4));
        assertEquals(15, sumRange(2, 4));
        assertEquals(7, sumRange(3, 3));
        update(4, 5);
        update(1, 7);
        update(0, 8);
        assertEquals(12, sumRange(1, 2));
        update(1, 9);
        assertEquals(5, sumRange(4, 4));
        update(3, 4);
    }
}
