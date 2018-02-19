package com.leetcode.linkednodes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class SplitLinkedListInParts {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode node = root;
        while (node != null) {
            node = node.next;
            length++;
        }

        int addon = length % k;
        int splitSize = length / k;
        ListNode pre = null;
        node = root;

        ListNode[] splites = new ListNode[k];
        for (int i = 0; i < k && node != null; ++i, --addon) {
            splites[i] = node;
            for (int j = 0; j < splitSize + (addon > 0 ? 1 : 0); ++j) {
                pre = node;
                node = node.next;
            }
            pre.next = null;
        }
        return splites;
    }

    @Test
    public void test0() {
        ListNode root = ListNode.build(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        int k = 3;
        ListNode[] expected = new ListNode[k];
        expected[0] = ListNode.build(new int[]{1, 2, 3, 4});
        expected[1] = ListNode.build(new int[]{5, 6, 7});
        expected[2] = ListNode.build(new int[]{8, 9, 10});

        ListNode[] actual = splitListToParts(root, k);
        for (int i = 0; i < k; ++i) {
            assertTrue(ListNode.sameList(expected[i], actual[i]));
        }
    }

    @Test
    public void test1() {
        ListNode root = ListNode.build(new int[]{});
        int k = 3;
        ListNode[] expected = new ListNode[k];
        expected[0] = ListNode.build(new int[]{});
        expected[1] = ListNode.build(new int[]{});
        expected[2] = ListNode.build(new int[]{});

        ListNode[] actual = splitListToParts(root, k);
        for (int i = 0; i < k; ++i) {
            assertTrue(ListNode.sameList(expected[i], actual[i]));
        }
    }

    @Test
    public void test2() {
        ListNode root = ListNode.build(new int[]{1, 2, 3, 4});
        int k = 5;
        ListNode[] expected = new ListNode[k];
        expected[0] = ListNode.build(new int[]{1});
        expected[1] = ListNode.build(new int[]{2});
        expected[2] = ListNode.build(new int[]{3});
        expected[3] = ListNode.build(new int[]{4});
        expected[4] = ListNode.build(new int[]{});

        ListNode[] actual = splitListToParts(root, k);

        for (int i = 0; i < k; ++i) {
            assertTrue(ListNode.sameList(expected[i], actual[i]));
        }
    }

    @Test
    public void test3() {
        ListNode root = ListNode.build(new int[]{0, 1, 2, 3, 4});
        int k = 3;
        ListNode[] expected = new ListNode[k];
        expected[0] = ListNode.build(new int[]{0, 1});
        expected[1] = ListNode.build(new int[]{2, 3});
        expected[2] = ListNode.build(new int[]{4});

        ListNode[] actual = splitListToParts(root, k);

        for (int i = 0; i < k; ++i) {
            assertTrue(ListNode.sameList(expected[i], actual[i]));
        }
    }
}
