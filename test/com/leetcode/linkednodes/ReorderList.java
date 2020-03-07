package com.leetcode.linkednodes;

import org.junit.Test;
import com.leetcode.util.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode node = slow.next;
        slow.next = null;
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        ListNode l1 = head;
        ListNode l2 = prev;
        while (l1 != null && l2 != null) {
            ListNode next = l2.next;
            l2.next = l1.next;
            l1.next = l2;
            l2 = next;
            l1 = l1.next.next;
        }
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build("1->2->3->4->5->6");
        ListNode expected = ListNode.build("1->6->2->5->3->4");

        reorderList(head);
        ListNode.assertEquals(expected, head);
    }

    @Test
    public void test1() {
        ListNode head = ListNode.build("1->2->3->4->5->6->7");
        ListNode expected = ListNode.build("1->7->2->6->3->5->4");
        reorderList(head);
        ListNode.assertEquals(expected, head);

    }
}
