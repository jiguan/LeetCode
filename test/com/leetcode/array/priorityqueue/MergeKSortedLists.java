package com.leetcode.array.priorityqueue;
import java.util.PriorityQueue;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (n1, n2) -> n1.val - n2.val);
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        ListNode fakeHead = new ListNode(-1);
        ListNode node = fakeHead;
        while (!queue.isEmpty()) {
            node.next = queue.poll();
            node = node.next;

            if (node.next != null) {
                queue.add(node.next);
            }
        }

        return fakeHead.next;
    }

    @Test
    public void test0() {
        ListNode[] lists = new ListNode[]{ListNode.build(new int[]{1, 4, 5}), ListNode.build(new int[]{1, 3, 4}),
                ListNode.build(new int[]{2, 6})};
        ListNode res = mergeKLists(lists);
        PrettyPrint.print(res);
    }
}
