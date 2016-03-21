package practice.first.array;

import org.junit.Test;

import practice.first.util.ListNode;

/*
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 */

public class OddEvenLinkedList {
	public ListNode oddEvenList0(ListNode head) {
		if (head == null)
			return head;
		ListNode fakeOddHead = new ListNode(0), fakeEvenHead = new ListNode(0);
		ListNode oddNode = fakeOddHead, evenNode = fakeEvenHead;
		ListNode node = head;
		boolean odd = true;
		while (node != null) {
			if (odd) {
				oddNode.next = node;
				oddNode = node;
			} else {
				evenNode.next = node;
				evenNode = node;
			}
			node = node.next;
			odd = !odd;
		}
		oddNode.next = fakeEvenHead.next;
		evenNode.next = null;
		return fakeOddHead.next;
	}

	public ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode odd = head, even = head.next;
		ListNode evenHead = even;
		while (odd.next != null && even.next != null) {
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}

	@Test
	public void test0() {
		int[] arr = new int[] { 1, 2, 3, 4, 5 };
		ListNode head = convert(arr);
		prettyPrint(head);
	}

	@Test
	public void test1() {
		int[] arr = new int[] { 1, 2, 3, 4, 5 };
		ListNode head = convert(arr);
		prettyPrint(oddEvenList(head));
	}

	@Test
	public void test2() {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6 };
		ListNode head = convert(arr);
		prettyPrint(oddEvenList(head));
	}

	@Test
	public void test3() {
		ListNode head = new ListNode(0);
		prettyPrint(oddEvenList(head));
	}

	private ListNode convert(int[] arr) {
		ListNode head = new ListNode(arr[0]);
		ListNode node = head;
		for (int i = 1; i < arr.length; i++) {
			node.next = new ListNode(arr[i]);
			node = node.next;
		}
		return head;
	}

	private void prettyPrint(ListNode node) {
		while (node != null) {
			System.out.print(node.val + "->");
			node = node.next;
		}
		System.out.println("null");
	}
}
