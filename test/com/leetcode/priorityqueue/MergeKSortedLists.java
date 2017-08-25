package com.leetcode.priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.leetcode.util.ListNode;

public class MergeKSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists==null || lists.length==0) return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val) {
					return -1;
				} else if (o1.val > o2.val) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		for(ListNode node : lists) {
			queue.add(node);
		}

		ListNode fakeHead = new ListNode(-1);
		ListNode node = fakeHead;
		while(!queue.isEmpty()) {
			node.next = queue.poll();
			node = node.next;
		}
		
		return fakeHead.next;
	}
}
