package com.leetcode.array;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {
        if(head==null||k==0) return head;
		ListNode node = head;
		int len = 1;
		while(node.next!=null) {
			len++;
			node = node.next;
		}
		node.next = head;
		k %= len;
		for(int i=0;i<len-k;i++) {
			node = node.next;
		}
		head = node.next;
		node.next = null;
		
		return head;
	}
	
	private void prettyPrint(ListNode node) {
		while(node!=null) {
			System.out.print(node.val+"->");
			node = node.next;
		}
		System.out.println("null");
	}
	
	private ListNode assemble(int[] arr) {
		ListNode head = new ListNode(arr[0]);
		ListNode prev = head;
		for(int i =1;i<arr.length;i++) {
			ListNode node = new ListNode(arr[i]);
			prev.next = node;
			prev = node;
		}
		return head;
	}
	
	@Test
	public void test0() {
		ListNode head = assemble(new int[]{1,2,3,4,5,6,7,8});
		ListNode returnHead = rotateRight(head, 3);
		prettyPrint(returnHead);
	}
	
	@Test
	public void test1() {
		ListNode head = assemble(new int[]{1});
		ListNode returnHead = rotateRight(head, 99);
		prettyPrint(returnHead);
	}
}
