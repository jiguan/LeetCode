package com.leetcode.linkednodes;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode pre = fake;
        for (int i = 1; i < m; ++i) {
            pre = pre.next;
        }

        ListNode node = pre.next; // beginning of the sub-list

        // start is fixed on its node, always the last one
        for (int i = m; i < n; ++i) {
            ListNode next = node.next;
            node.next = next.next;
            next.next = pre.next; // not node!
            pre.next = next;
        }

        return fake.next;
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        // Expect 1->4->3->2-5
        PrettyPrint.print(reverseBetween(head, 2, 4));
    }
}
