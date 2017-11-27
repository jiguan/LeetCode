package com.leetcode.linkednodes;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map = new HashMap<>();
    private Node head, tail;

    // In order to run junit, a default constructor is a must.
    public LRUCache() {
        this.capacity = 10;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        reorder(node);
        return node.value;
    }

    private void reorder(Node node) {
        if (head == null)
            head = node;
        if (tail == null)
            tail = node;

        if (head == node)
            return;

        delete(node);
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
        } else {
            node = new Node(key, value);
        }

        reorder(node);
        map.put(key, node);
        if (map.size() > capacity) {
            map.remove(tail.key);
            delete(tail);
        }
    }

    // Clean up node's relationship, don't manipulate the map.
    private void delete(Node node) {
        if (node == head) {
            head = null;
            tail = null;
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            // Deal with node's adjacent nodes first, don't change node itself.
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            node.next = null;
            node.prev = null;
        }
    }

    private class Node {
        int key;
        int value;
        Node prev = null;
        Node next = null;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + " - " + value + "]";
        }
    }

    private void prettyPrint(Node node) {
        while (node != null) {
            System.out.print("[" + node.key + "-" + node.value + "] -> ");
            node = node.next;
        }
        System.out.println("null");
    }

    @Test
    public void test0() {
        LRUCache cache = new LRUCache();
        cache.capacity = 2;
        cache.put(1, 1);
        cache.put(2, 2);
        prettyPrint(cache.head);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        prettyPrint(cache.head);
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }
    
    @Test
    public void test1() {
        LRUCache cache = new LRUCache();
        cache.capacity = 3;
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        assertEquals(4, cache.get(4));
        prettyPrint(cache.head);
        assertEquals(3, cache.get(3));
        assertEquals(2, cache.get(2));
        assertEquals(-1, cache.get(1));
        prettyPrint(cache.head);
        cache.put(5, 5);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(-1, cache.get(4));
        assertEquals(5, cache.get(5));
    }
    
    @Test
    public void test2() {
        LRUCache cache = new LRUCache();
        cache.capacity = 1;
        cache.put(2, 1);
        assertEquals(1, cache.get(2));
        cache.put(3, 2);
        assertEquals(-1, cache.get(2));
        assertEquals(2, cache.get(3));
    }
    
    @Test
    public void test3() {
        LRUCache cache = new LRUCache();
        cache.capacity = 2;
        cache.put(2, 1);
        cache.put(1, 1);
        assertEquals(1, cache.get(2));
        cache.put(4, 1);
        assertEquals(-1, cache.get(1));
        assertEquals(1, cache.get(2));
    }
}
