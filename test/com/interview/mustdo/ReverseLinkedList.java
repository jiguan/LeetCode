package com.interview.mustdo;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class ReverseLinkedList {
    // A -> B -> C -> D -> A
    public ListNode reverseList(ListNode head) {
    	ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    @Test
    public void test0() {
        ListNode root = new ListNode(0);
        root.next = new ListNode(1);
        root.next.next = new ListNode(2);
        root.next.next.next = root;
        // 0 1 2
        PrettyPrint.print(root);
        PrettyPrint.print(reverseList(root));
    }

}
