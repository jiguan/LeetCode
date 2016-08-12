package com.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SegmentTreeRangeSum {
	// https://www.youtube.com/watch?v=ZBHKZF5w4YU
	/*
	 * Segment tree's leaf node is the element, non-leaf node record its
	 * children's rang and sum
	 */

	int[] nums;
	SegmentTreeNode root;

	public void init(int[] nums) {
		this.nums = nums;
		root = build(0, nums.length - 1);
	}

	private SegmentTreeNode build(int start, int end) {
		SegmentTreeNode root = new SegmentTreeNode();
		root.range[0] = start;
		root.range[1] = end;
		if (start == end) {
			root.sum = nums[start];
			return root;
		}
		int mid = (start + end) / 2;
		root.left = build(start, mid);
		root.right = build(mid + 1, end);
		root.sum = root.left.sum + root.right.sum;
		return root;
	}

	public int query(int start, int end) {
		return query(root, start, end);
	}

	private int query(SegmentTreeNode node, int start, int end) {
		if (node.range[0] == start && node.range[1] == end) {
			return node.sum;
		}
		int mid = node.range[0] + (node.range[1] - node.range[0]) / 2;
		if (mid >= end) {
			return query(node.left, start, end);
		} else if (mid + 1 <= start) {
			return query(node.right, start, end);
		} else {
			return query(node.left, start, mid) + query(node.right, mid + 1, end);
		}
	}

	public void update(int index, int value) {
		int diff = value - nums[index];
		nums[index] = value;
		update(root, index, diff);
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

	private class SegmentTreeNode {
		int[] range = new int[2];
		int sum = 0;
		SegmentTreeNode left = null;
		SegmentTreeNode right = null;

		@Override
		public String toString() {
			return "SegmentTreeNode{range: [" + range[0] + ", " + range[1] + "], sum: " + sum + ", left: " + left
					+ ", right: " + right + "} ";
		}
	}

	@Test
	public void test0() {
		int[] nums = new int[] { 1, 3, 5 };
		init(nums);
		assertNotNull(root);
		assertEquals(9, query(root, 0, 2));
		assertEquals(4, query(root, 0, 1));
	}

	@Test
	public void test1() {
		int[] nums = new int[] { 1, 3, 5 };
		init(nums);
		update(1, -3);
		assertEquals(3, query(root, 0, 2));
		assertEquals(-2, query(root, 0, 1));
		assertEquals(5, query(root, 2, 2));
	}
}
