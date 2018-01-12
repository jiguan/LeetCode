package com.leetcode.linkednodes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode node = head;
        while (node != null && node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        return head;
    }

    @Test
    public void test() {
        ListNode head = ListNode.build(new int[]{1, 2, 6, 3, 4, 5, 6});
        ListNode expected = ListNode.build(new int[]{1, 2, 3, 4, 5});
        assertTrue(ListNode.sameList(expected, removeElements(head, 6)));
    }
}
