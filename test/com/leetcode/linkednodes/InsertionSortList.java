package com.leetcode.linkednodes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		ListNode fake = new ListNode(-1);
		ListNode pre = fake, node = head;
		while (node != null) {
			pre = fake;
			while (pre.next != null && pre.next.val < node.val) {
				pre = pre.next;
			}
			ListNode next = node.next;
			node.next = pre.next;
			pre.next = node;
			node = next;
		}
		return fake.next;
	}

	@Test
	public void test0() {
		ListNode root = ListNode.build(new int[] { 5, 4, 3, 2, 1 });
		ListNode expected = ListNode.build(new int[] { 1, 2, 3, 4, 5 });
		ListNode actual = insertionSortList(root);
		assertTrue(ListNode.sameList(expected, actual));
	}
}
