package com.leetcode.design;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DesignHashSet {

    @Test
    public void test0() {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        assertTrue(hashSet.contains(1)); // returns true
        assertFalse(hashSet.contains(3)); // returns false (not found)
        hashSet.add(2);
        assertTrue(hashSet.contains(2)); // returns true
        hashSet.remove(2);
        assertFalse(hashSet.contains(2));
    }

    /*
     * ["MyHashSet","add","remove","add","remove","remove","add","add","add","add","remove"]
     * [[],[9],[19],[14],[19],[9],[0],[3],[4],[0],[9]]
     */

    @Test
    public void test1() {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(9);
        hashSet.remove(19);
        hashSet.add(14);
        hashSet.remove(19);
        hashSet.remove(9);
        hashSet.add(0);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(0);
        hashSet.remove(9);
    }
}


class MyHashSet {

    Node[] nodes = new Node[1000];

    public void add(int key) {
        int index = getIndex(key);
        if (nodes[index] == null) {
            nodes[index] = new Node(-1);
        }
        Node prev = findPrev(key);
        if (prev.next == null) {
            // we cannot find the node with this value
            prev.next = new Node(key);
        }
    }

    private int getIndex(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    public void remove(int key) {
        Node prev = findPrev(key);
        if (prev != null && prev.next != null) {
            prev.next = prev.next.next;
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        Node prev = findPrev(key);
        return prev != null && prev.next != null;
    }

    // we know the first one is not the key
    private Node findPrev(int key) {
        int index = getIndex(key);
        Node prev = nodes[index];
        if (prev != null) {
            Node node = prev.next;
            while (node != null && node.val != key) {
                prev = node;
                node = node.next;
            }
        }
        return prev;
    }

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}


