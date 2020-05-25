package com.leetcode.util;

public class ImmutableListNode {
    private int val;
    private ImmutableListNode next;

    private ImmutableListNode(int val) {
        this.val = val;
    }

    public void printValue() {
        System.out.println(val);
    }

    public ImmutableListNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return this.val + "->" + next;
    }

    public static ImmutableListNode build(int[] nums) {
        ImmutableListNode root = new ImmutableListNode(0);
        ImmutableListNode prev = root;
        for (int num : nums) {
            ImmutableListNode node = new ImmutableListNode(num);
            prev.next = node;
            prev = node;
        }
        return root.next;
    }
}
