package com.leetcode.implement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DesignHashMap {

	@Test
	public void test0() {
		MyHashMap hashmap = new MyHashMap();
		hashmap.put(1, 1);
		hashmap.put(2, 2);
		assertEquals(1, hashmap.get(1));
		assertEquals(-1, hashmap.get(3));
		hashmap.put(2, 1);
		assertEquals(1, hashmap.get(2));
		hashmap.remove(2);
		assertEquals(-1, hashmap.get(2));
	}

}

class MyHashMap {
	private ListNode[] nodes = new ListNode[10000];

	/** value will always be non-negative. */
	public void put(int key, int value) {
		int i = index(key);
		if (nodes[i] == null) {
			// add a fake head
			nodes[i] = new ListNode(-1, -1);
		}
		ListNode prev = findPrev(nodes[i], key);
		if (prev.next != null) {
			// prev.next (node) not null = we have this key
			prev.next.val = value;
		} else {
			prev.next = new ListNode(key, value);
		}
	}

	/**
	 * Returns the value to which the specified key is mapped, or -1 if this map
	 * contains no mapping for the key
	 */
	public int get(int key) {
		int i = index(key);
		if (nodes[i] == null) {
			return -1;
		}
		ListNode prev = findPrev(nodes[i], key);
		return prev.next != null ? prev.next.val : -1;
	}

	/**
	 * Removes the mapping of the specified value key if this map contains a mapping
	 * for the key
	 */
	public void remove(int key) {
		int i = index(key);
		if (nodes[i] == null)
			return;
		ListNode prev = findPrev(nodes[i], key);
		if (prev.next != null) {
			ListNode next = prev.next.next;
			prev.next = next;
		}
	}

	private int index(int key) {
		return Integer.hashCode(key) % nodes.length;
	}

	private ListNode findPrev(ListNode bucket, int key) {
		ListNode node = bucket, prev = null;
		while (node != null && node.key != key) {
			prev = node;
			node = node.next;
		}
		// return prev so that we could append one more if needed
		return prev;
	}
	
	class ListNode {
	    int key, val;
	    ListNode next;

	    ListNode(int key, int val) {
	        this.key = key;
	        this.val = val;
	    }
	}
}


