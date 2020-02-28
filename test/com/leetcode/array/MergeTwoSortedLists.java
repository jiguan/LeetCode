package com.leetcode.array;

import com.leetcode.util.ListNode;

public class MergeTwoSortedLists {
	public ListNode mergeTwoLists0(ListNode l1, ListNode l2) {
		ListNode fake = new ListNode(-1);
		ListNode node = fake;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				node.next = l2;
				break;
			} else if (l2 == null) {
				node.next = l1;
				break;
			} else {
				if (l1.val < l2.val) {
					node.next = l1;
					l1 = l1.next;
				} else {
					node.next = l2;
					l2 = l2.next;
				}
				node = node.next;
			}
		}
		return fake.next;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

}
