package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leetcode.util.ListNode;

public class IntersectionOfTwoLinkedLists {
	public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
		if(headA==null||headB==null) return null;
		
		ListNode node1 = headA, node2 = headB;
		while(node1!=node2) {
			node1 = (node1==null) ? headB : node1.next;
			node2 = (node2==null) ? headA : node2.next;
		}
		return node1;
	}
	
	  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        if(headA==null||headB==null) return null;
			
		    int len1 = 0, len2= 0;
		    ListNode node1 = headA, node2=headB;
		    while(node1!=null || node2!=null) {
		        if(node1!=null) {
		            len1++;
		            node1 = node1.next;
		        }
		        if(node2!=null) {
		            len2++;
		            node2 = node2.next;
		        }
		    }
		    
		    node1 = (len1 > len2) ? headA : headB; 
		    node2 = (len1 > len2) ? headB : headA;
		    for(int i=0;i<Math.abs(len1-len2);i++) {
		        node1 = node1.next;
		    }
		   ;
		    while(node1!=node2) {
		        node1 = node1.next;
		        node2 = node2.next;
		    }
		    return node1;
	    }
	  
	  @Test
	  public void test0() {
		  ListNode node0 = new ListNode(0);
		  ListNode node1 = new ListNode(1);
		  ListNode node2 = new ListNode(2);
		  ListNode node3 = new ListNode(3);
		  node0.next = node1;
		  node1.next = node2;
		  node2.next = node3;
		  
		  ListNode node4 = new ListNode(4);
		  node4.next = node2;
		  assertEquals(node2, getIntersectionNode(node0, node4));

	  }
	  
}
