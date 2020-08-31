package com.leetcode.design;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class LeastRecentlyUsedCache {
    @Test
    public void test0() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        LRUCache.prettyPrint(cache.head);
        assertEquals(1, cache.get(1));
        LRUCache.prettyPrint(cache.head);
        cache.put(3, 3);
        LRUCache.prettyPrint(cache.head);
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void test1() {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        assertEquals(4, cache.get(4));
        LRUCache.prettyPrint(cache.head);
        assertEquals(3, cache.get(3));
        assertEquals(2, cache.get(2));
        assertEquals(-1, cache.get(1));
        LRUCache.prettyPrint(cache.head);
        cache.put(5, 5);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(-1, cache.get(4));
        assertEquals(5, cache.get(5));
    }

    @Test
    public void test2() {
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        assertEquals(1, cache.get(2));
        cache.put(3, 2);
        assertEquals(-1, cache.get(2));
        assertEquals(2, cache.get(3));
    }

    @Test
    public void test3() {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        assertEquals(1, cache.get(2));
        cache.put(4, 1);
        assertEquals(-1, cache.get(1));
        assertEquals(1, cache.get(2));
    }

    @Test
    public void test4() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertEquals(2, cache.get(2));
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(1, cache.get(3));
        assertEquals(4, cache.get(4));
    }
}

class LRUCache {
    int capacity;
    Map<Integer, Node> map;
    Node head = new Node(), tail = new Node();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            update(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            update(node);
        } else {
            if (map.size() >= capacity) {
                // update map first, since we get the key by tail.prev
                // delete(tail.prev) will change the tail node
                map.remove(tail.prev.key);
                delete(tail.prev);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            update(node);
        }
    }

    private void update(Node node) {
        delete(node);

        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }


    // only func relies on itself
    private void delete(Node node) {
        // Since delete() is also called from update(). For newly created node, prev and next are
        // null
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    class Node {
        Node prev, next;
        // key is needed since we need to remove node from Map
        int value, key;

        public Node() {};

        public Node(int key, int value) {
            this.value = value;
            this.key = key;
        }

        @Override
        public String toString() {
            return "[" + key + " - " + value + "]";
        }
    }

    static void prettyPrint(Node node) {
        while (node != null) {
            System.out.print("[" + node.key + "-" + node.value + "] -> ");
            node = node.next;
        }
        System.out.println("null");
    }
}


