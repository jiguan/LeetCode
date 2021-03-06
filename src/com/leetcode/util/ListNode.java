package com.leetcode.util;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    public static ListNode build(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        ListNode head = new ListNode(nums[0]);
        ListNode prev = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            prev.next = node;
            prev = node;
        }
        return head;
    }

    /**
     * Parse string representation into a list of linked nodes
     * 
     * @param list - e.g. "1->4->3->2->5->2"
     * @return the head node of the list nodes
     */
    public static ListNode build(String list) {
        List<String> values = Arrays.asList(list.split("->"));
        ListNode fake = new ListNode(-1);
        ListNode pre = fake;
        for (String value : values) {
            ListNode node = new ListNode(Integer.parseInt(value));
            pre.next = node;
            pre = node;
        }
        return fake.next;
    }

    public static boolean sameList(ListNode r1, ListNode r2) {
        while (r1 != null && r2 != null) {
            if (r1.val != r2.val) return false;
            r1 = r1.next;
            r2 = r2.next;
        }
        if (r1 == null && r2 == null) {
            return true;
        } else {
            return false;
        }
    }

    public static void assertEquals(ListNode expected, ListNode actual) {
        String message = "ListNodes are not equal, expected: %d, actual: %d";
        if (expected == null && actual == null) {
            return;
        } else if (expected == null || actual == null) {
            Assert.fail(String.format(message, expected != null ? expected.val : null,
                    actual != null ? actual.val : null));
        } else if (expected.val != actual.val) {
            Assert.fail(String.format(message, expected.val, actual.val));
        } else {
            assertEquals(expected.next, actual.next);
        }
    }
}
