package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int sum = 0;
		ListNode fake = new ListNode(-1);
		ListNode node = fake;

		while (l1 != null || l2 != null || sum != 0) {
			int v1 = 0;
			if (l1 != null) {
				v1 = l1.val;
				l1 = l1.next;
			}
			int v2 = 0;
			if (l2 != null) {
				v2 = l2.val;
				l2 = l2.next;
			}
			sum = sum + v1 + v2;
			node.next = new ListNode(sum % 10);
			sum /= 10;
			node = node.next;
		}
		return fake.next;
	}

	@Test
	public void test0() {
		ListNode l1 = ListNode.build(new int[] { 2, 4, 3 });
		ListNode l2 = ListNode.build(new int[] { 5, 6, 4 });
		ListNode expected = ListNode.build(new int[] { 7, 0, 8 });
		assertTrue(ListNode.sameList(expected, addTwoNumbers(l1, l2)));
	}

	@Test
	public void test1() {
		ListNode l1 = ListNode.build(new int[] { 5 });
		ListNode l2 = ListNode.build(new int[] { 5 });
		ListNode expected = ListNode.build(new int[] { 0, 1 });
		assertTrue(ListNode.sameList(expected, addTwoNumbers(l1, l2)));
	}
}
