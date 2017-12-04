package com.leetcode.linkednodes;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode pre = fake;
        for (int i = 0; i < m - 1; ++i) {
            pre = pre.next;
        }

        ListNode start = pre.next; // beginning of the sub-list
        ListNode node = start.next; // node is going to be reversed

        // start is fixed on its node, always the last one
        for (int i = 0; i < n - m; ++i) {
            start.next = node.next;
            node.next = pre.next;
            pre.next = node;
            node = start.next;
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
