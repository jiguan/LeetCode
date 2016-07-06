package com.leetcode.util;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	public static ListNode build(int[] nums) {
		ListNode head = new ListNode(nums[0]);
		ListNode prev = head;
		for(int i = 1;i<nums.length;i++) {
			ListNode node = new ListNode(nums[i]);
			prev.next = node;
			prev = node;
		}
		return head;
	}
}