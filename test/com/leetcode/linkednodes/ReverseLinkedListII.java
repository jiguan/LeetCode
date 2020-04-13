package com.leetcode.linkednodes;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.leetcode.util.ListNode;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode prev = fake;
        for (int i = 1; i < m; ++i) {
            prev = prev.next;
        }
        ListNode node = prev.next;
        ListNode anchor = prev;
        // prev __ node -> next
        // prev <- node __ next
        for (int i = m; i <= n; ++i) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        // anchor.next was the head, now it becomes the tail
        anchor.next.next = node;
        // connect the new head
        anchor.next = prev;
        return fake.next;
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build(new int[] {1, 2, 3, 4, 5});
        ListNode expected = ListNode.build(new int[] {1, 4, 3, 2, 5});
        assertTrue(ListNode.sameList(expected, reverseBetween(head, 2, 4)));
    }
}
