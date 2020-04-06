package com.leetcode.linkednodes;

import org.junit.Test;
import com.leetcode.util.ListNode;

public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode fake = new ListNode(-1);
        fake.next = head;

        ListNode prev = fake;
        ListNode node = head;
        while (node != null) {
            ListNode fast = node;
            int i = 0;
            while (i < k && fast != null) {
                fast = fast.next;
                ++i;
            }
            if (i < k) break;
            // reverse return the new head, node is the tail now
            // e.g. -1 -> 1 (node) -> 2 -> 3, k = 2
            // prev = -1, fast = 3, node = 1
            // prev.next = 2 -> 1 (node) -> -1
            prev.next = reverse(prev, fast);
            // move prev pointing to node, current tail
            prev = node;
            // 2 -> 1 (node) -> 3
            node.next = fast;
            node = fast;
        }
        return fake.next;
    }

    private ListNode reverse(ListNode prev, ListNode tail) {
        ListNode node = prev.next;
        while (node != tail) {
            ListNode next = node.next;
            node.next = prev;
            prev= node;
            node = next;
        }
        return prev;
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build(new int[] {1, 2, 3, 4, 5});
        ListNode expected = ListNode.build(new int[] {2, 1, 4, 3, 5});
        ListNode actual = reverseKGroup(head, 2);
        ListNode.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        ListNode head = ListNode.build(new int[] {1, 2, 3, 4, 5});
        ListNode expected = ListNode.build(new int[] {3, 2, 1, 4, 5});
        ListNode.assertEquals(expected, reverseKGroup(head, 3));
    }

    @Test
    public void test2() {
        ListNode head = ListNode.build(new int[] {1, 2});
        ListNode expected = ListNode.build(new int[] {2, 1});
        ListNode.assertEquals(expected, reverseKGroup(head, 2));
    }
}
