package com.leetcode.linkednodes;

import java.util.Random;

import com.leetcode.util.ListNode;

public class LinkedListRandomNode {
    private Random random = new Random();
    private ListNode head;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }

    /*
     * The probability to select i but not select following:
     * 1/i * (1 - 1/i+1) * (1 - 1/i+2) * ... * (1 - 1/n) = 1/n
     */
    public int getRandom() {
        ListNode node = head;
        int res = node.val;
        // determine whether take a new one
        for (int i = 1; node.next != null; ++i) {
            node = node.next;
            // [0, i+1), there is 1/i+1 probability to select a new one
            if (random.nextInt(i + 1) == i) res = node.val;
        }
        return res;
    }
}
