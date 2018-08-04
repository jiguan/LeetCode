package com.leetcode.linkednodes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class PairwiseSwapElementsInLinkedList {
    public ListNode swap(ListNode root) {
        if (root == null) return null;
        ListNode fake = new ListNode(0);
        fake.next = root;
        ListNode prev = fake;
        ListNode slow = root;

        while (slow != null && slow.next != null) {
            ListNode fast = slow.next;
            ListNode next = fast.next;
            prev.next = fast;
            fast.next = slow;
            slow.next = next;

            prev = slow;
            slow = next;
        }
        return fake.next;
    }

    @Test
    public void test0() {
        ListNode root = ListNode.build(new int[]{0, 1, 2, 3, 4, 5});
        ListNode expected = ListNode.build(new int[]{1, 0, 3, 2, 5, 4});
        assertTrue(ListNode.sameList(expected, swap(root)));
    }

    @Test
    public void test1() {
        ListNode root = ListNode.build(new int[]{0, 1, 2, 3, 4});
        ListNode expected = ListNode.build(new int[]{1, 0, 3, 2, 4});
        assertTrue(ListNode.sameList(expected, swap(root)));
    }
}
