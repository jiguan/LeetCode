package com.leetcode.linkednodes;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(-1), large = new ListNode(-1);
        ListNode fakeLess = less, fakeLarge = large;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = head;
            } else {
                large.next = head;
                large = head;
            }
            head = head.next;
        }
        large.next = null;
        less.next = fakeLarge.next;;
        
        return fakeLess.next;
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build("1->4->3->2->5->2");
        PrettyPrint.print(partition(head, 3));
    }
}
