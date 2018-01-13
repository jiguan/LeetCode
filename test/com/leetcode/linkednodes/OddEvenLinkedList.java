package com.leetcode.linkednodes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    @Test
    public void test0() {
        ListNode root = ListNode.build(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        ListNode expected = ListNode.build(new int[]{1, 3, 5, 7, 2, 4, 6, 8});
        assertTrue(ListNode.sameList(expected, oddEvenList(root)));
    }

    @Test
    public void test1() {
        ListNode root = ListNode.build(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode expected = ListNode.build(new int[]{1, 3, 5, 7, 2, 4, 6});
        assertTrue(ListNode.sameList(expected, oddEvenList(root)));
    }
}
