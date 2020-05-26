package com.leetcode.linkednodes;

/*
 * Insert into a Sorted Circular Linked List
 * 
 * Given a node from a Circular Linked List which is sorted in ascending order, write a function to
 * insert a value insertVal into the list such that it remains a sorted circular list. The given
 * node can be a reference to any single node in the list, and may not be necessarily the smallest
 * value in the circular list.
 * 
 * If there are multiple suitable places for insertion, you may choose any place to insert the new
 * value. After the insertion, the circular list should remain sorted.
 * 
 * If the list is empty (i.e., given node is null), you should create a new single circular list and
 * return the reference to that single node. Otherwise, you should return the original given node.
 * 
 * Example 1:
 * @formatter:off
 * 1--\
 * ^  |
 * |  |
 * 4<-3 (head)
 * @formatter:on
 * Input: head = [3,4,1], insertVal = 2 Output: [3,4,1,2] Explanation: In the figure above, there is
 * a sorted circular list of three elements. You are given a reference to the node with value 3, and
 * we need to insert 2 into the list. The new node should be inserted between node 1 and node 3.
 * After the insertion, the list should look like this, and we should still return node 3.
 *
 * @formatter:off
 * 1->2
 * ^  |
 * |  |
 * 4<-3 (head)
 * @formatter:on
 * 
 * Example 2:
 * 
 * Input: head = [], insertVal = 1 Output: [1] Explanation: The list is empty (given head is null).
 * We create a new single circular list and return the reference to that single node. Example 3:
 * 
 * Input: head = [1], insertVal = 0 Output: [1,0]
 * 
 */
public class InsertIntoASortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        // if start is null, create a node pointing to itself and return
        if (head == null) {
            Node node = new Node(insertVal, null);
            node.next = node;
            return node;
        }
        // if start is not null, try to insert it into correct position
        // 1st pass to find max node
        Node cur = head;
        while (cur.val <= cur.next.val && cur.next != head)
            cur = cur.next;
        // 2nd pass to insert the node in to correct position
        Node max = cur;
        Node dummy = new Node(0, max.next); // use a dummy head to make insertion process simpler
        max.next = null; // break the cycle
        cur = dummy;
        while (cur.next != null && cur.next.val < insertVal) {
            cur = cur.next;
        }
        cur.next = new Node(insertVal, cur.next); // insert
        Node newMax = max.next == null ? max : max.next; // reconnect to cycle
        newMax.next = dummy.next;
        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
}
