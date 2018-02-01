package com.leetcode.divideandconquer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        return partion(lists, 0, lists.length - 1);
    }

    private ListNode partion(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        if (start < end) {
            int mid = (end - start) / 2 + start;
            ListNode l1 = partion(lists, 0, mid);
            ListNode l2 = partion(lists, mid + 1, end);
            return merge(l1, l2);
        }
        return null;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode fake = new ListNode(0);
        ListNode node = fake;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int v2 = l2 == null ? Integer.MAX_VALUE : l2.val;

            if (v1 < v2) {
                node.next = l1;
                node = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                node = l2;
                l2 = l2.next;
            }
        }
        return fake.next;
    }

    @Test
    public void test0() {
        ListNode[] lists = new ListNode[3];
        lists[0] = ListNode.build(new int[] { 1, 4, 7 });
        lists[1] = ListNode.build(new int[] { 2, 5, 8 });
        lists[2] = ListNode.build(new int[] { 3, 6, 9 });
        ListNode expected = ListNode.build(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        assertTrue(ListNode.sameList(expected, mergeKLists(lists)));
    }
}
