package com.leetcode.tree;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class ReverseLinkedList {
    ListNode newHead = null;
    
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head!=null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    
    public ListNode reverseList0(ListNode head) {
        if(head==null||head.next==null) return head;
        reverse(head);
        return newHead;
    }

    public ListNode reverse(ListNode head) {
        if (head.next == null) {
            newHead = head;
            return head;
        }
        ListNode node = reverse(head.next);
        node.next = head;
        head.next = null;
        return head;
    }

    private void prettyPrint(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }

    @Test
    public void test0() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        prettyPrint(reverseList(node1));

    }
    
    @Test
    public void test1() {
        ListNode node1 = new ListNode(1);
        prettyPrint(reverseList(node1));

    }


}
