package com.leetcode.linkedlist;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode pre = fake;
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            pre.next = head.next;
            head.next.next = head;
            head.next = next;
            pre = head;
            head = next;
        }
        return fake.next;
    }

    @Test
    public void test() {
        ListNode head = ListNode.build(new int[] {1, 2, 3, 4, 5});
        PrettyPrint.print(swapPairs(head));
    }
}
