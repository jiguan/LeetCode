package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LRUCache {
	Map<Integer, Node> map;
	Node head = null, tail = null;
	int capacity;
	int count = 0;

	public int get(int key) {
		if(map.containsKey(key)) {
			Node node = map.get(key);
			reorder(node);
			return node.value;
		} else {
			return -1;
		}
	}
	
	public void set(int key, int value) {
		if(!map.containsKey(key)) {
			if(count==capacity) {
				map.remove(tail.key);
				deleteNode(tail);
				count--;
			}

			Node node = new Node(key, value);
			map.put(key, node);
			count++;
			if(count==1) {
				head = node;
				tail = node;
			}
			reorder(node);
		} else {
			Node node = map.get(key);
			node.value = value;
			reorder(node);
		}
		 
	}
	
	private void deleteNode(Node node) {
		if(node.key==tail.key) {
			tail = tail.pre;
		}
		if(node.pre!=null) {
			node.pre.next = node.next;
		}
		if(node.next!=null) {
			node.next.pre = node.pre;
		}
	}
	
	private void reorder(Node node) {
		if(node.key==head.key) return;
		deleteNode(node);
		node.next = head;
		head.pre = node;
		head = node;
	}
	
	
	
	
	
	
	private class Node {
		int key;
		int value;
		Node pre = null;
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

	public LRUCache() {
		this.capacity = 10;
		map = new HashMap<>(10);
	}

	public void build(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>(capacity);
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
		cache.build(4);
		cache.set(1, 1);
		cache.set(2, 2);
		cache.set(3, 3);
		cache.set(4, 4);

		assertEquals(1, cache.get(1));
		assertEquals(2, cache.get(2));
		assertEquals(3, cache.get(3));
		cache.set(5, 5);
		assertEquals(-1, cache.get(4));
	}

	
	@Test
	public void test1() {
		LRUCache cache = new LRUCache();
		cache.build(1);
		cache.set(1, 1);
		cache.set(2, 2);
		assertEquals(-1, cache.get(1));
		assertEquals(2, cache.get(2));
	}

	@Test
	public void test2() {
		LRUCache cache = new LRUCache();
		cache.build(1);
		cache.set(2, 1);
		cache.get(2);
		cache.set(3, 2);
		cache.get(2);
		cache.get(3);

		
	}

	@Test
	public void test3() {
		LRUCache cache = new LRUCache();
		cache.build(2);
		cache.set(2, 1);
		cache.set(2, 2);
		assertEquals(2, cache.get(2));
		cache.set(1, 1);
		cache.set(4, 1);
		assertEquals(-1, cache.get(2));

	}

}
