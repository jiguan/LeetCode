package com.leetcode.linkednodes;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
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

        ListNode first = head;
        ListNode second = prev;
        while (first != null && second != null) {
            ListNode next = second.next;
            second.next = first.next;
            first.next = second;
            second = next;
            first = first.next.next;
        }
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build("1->2->3->4->5->6");
        reorderList(head);
        PrettyPrint.print(head);
    }

    @Test
    public void test1() {
        ListNode head = ListNode.build("1->2->3->4->5->6->7");
        reorderList(head);
        PrettyPrint.print(head);
    }
}
