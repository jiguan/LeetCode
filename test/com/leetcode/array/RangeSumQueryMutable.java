package com.leetcode.array;

public class RangeSumQueryMutable {
    private int[] nums;
    SegmentTreeNode root;

    public void NumArray(int[] nums) {
        this.nums = nums;
        root = build(0, nums.length - 1);
    }

    private SegmentTreeNode build(int start, int end) {
        if(start>end)   return null;
        SegmentTreeNode root = new SegmentTreeNode();
        root.range[0] = start;
        root.range[1] = end;
        if (start == end) {
            root.sum = nums[start];
        } else {
            int mid = (start + end) / 2;
            root.left = build(start, mid);
            root.right = build(mid + 1, end);
            root.sum = root.left.sum + root.right.sum;
        }
        return root;
    }

    void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        update(root, i, diff);
    }

    private void update(SegmentTreeNode node, int index, int diff) {
        if (node == null)
            return;
        if (node.range[0] <= index && node.range[1] >= index) {
            node.sum += diff;
            update(node.left, index, diff);
            update(node.right, index, diff);
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    private int sumRange(SegmentTreeNode node, int start, int end) {
        if (node==null||node.range[1] < start || node.range[0] > end) { // no overlap
            return 0;
        }
        if (node.range[0] >= start && node.range[1] <= end) { // complete overlap
            return node.sum;
        }
        // partial overlap
        return sumRange(node.left, start, end) + sumRange(node.right, start, end);
    }

    private class SegmentTreeNode {
        int[] range = new int[2];
        int sum = 0;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;

        @Override
        public String toString() {
            return "SegmentTreeNode{range: [" + range[0] + ", " + range[1] + "], sum: " + sum + ", left: " + left + ", right: " + right + "} ";
        }
    }
}
