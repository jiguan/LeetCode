package com.leetcode.string.palindrome;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class PalindromeLinkedList {
	public boolean isPalindrome(ListNode head) {
		if(head==null||head.next==null) return true;
		boolean flag = false;
		ListNode node = head.next;
		ListNode mid1 = head, mid2 = head;
		while (node != null) {
			if (flag) {
				mid1 = mid1.next;
			} else {
				mid2 = mid2.next;
			}
			flag = !flag;
			node = node.next;
		}
		ListNode firstHalfStart = head;
		ListNode secondHalfStart = null;
		while(mid2!=null) {
			ListNode next = mid2.next;
			mid2.next = secondHalfStart;
			secondHalfStart = mid2;
			mid2 = next;
		}
		mid1.next = null;
		
		while (firstHalfStart != null && secondHalfStart != null) {
			if (firstHalfStart.val != secondHalfStart.val)
				return false;
			firstHalfStart = firstHalfStart.next;
			secondHalfStart = secondHalfStart.next;
		}
		return true;
	}

	@Test
	public void test0() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(1);
		node1.next = node2;
		node2.next = node3;
		assertTrue(isPalindrome(node1));
	}
	
	@Test
	public void test1() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(2);
		ListNode node4 = new ListNode(1);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		assertTrue(isPalindrome(node1));
	}
	
	@Test
	public void test2() {
		ListNode node1 = new ListNode(1);
		assertTrue(isPalindrome(node1));
	}

	@Test
	public void test3() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(1);
		node1.next = node2;
		assertTrue(isPalindrome(node1));
	}
	
}
