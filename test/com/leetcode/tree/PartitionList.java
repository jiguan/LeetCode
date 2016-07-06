package com.leetcode.tree;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class PartitionList {
	public ListNode partition(ListNode head, int x) {
        if(head==null) return null;
		ListNode smallHead = new ListNode(0), bigHead = new ListNode(0);
		ListNode smaller = smallHead, bigger = bigHead;
		while(head!=null) {
			if(head.val<x) {
				smaller.next = head;
				smaller = head;
			} else {
				bigger.next = head;
				bigger = head;
			}
			head = head.next;
		}
		bigger.next = null;
		smaller.next = bigHead.next;
		return smallHead.next;
	}
	
	private void prettyPrint(ListNode node) {
		while(node!=null) {
			System.out.print(node.val+"->");
			node = node.next;
		}
		System.out.println("null");
	}
	
	private ListNode build(int[] nums) {
		if(nums.length==0) return null;
		ListNode head = new ListNode(nums[0]);
		ListNode node = head;
		for(int i=1;i<nums.length;i++) {
			ListNode tmp = new ListNode(nums[i]);
			node.next = tmp;
			node = tmp;
		}
		return head;
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{1,4,3,2,5,2};
		ListNode head = build(nums);
		prettyPrint(head);
		int x = 3;
		head = partition(head, x);
		prettyPrint(head);
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{1};
		ListNode head = build(nums);
		prettyPrint(head);
		int x = 0;
		head = partition(head, x);
		prettyPrint(head);
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{2,1};
		ListNode head = build(nums);
		prettyPrint(head);
		int x = 2;
		head = partition(head, x);
		prettyPrint(head);
	}
	
}
