package practice.first.array;

import org.junit.Test;

import practice.first.util.ListNode;
import practice.first.util.PrettyPrint;

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode fakeHead = new ListNode(0);
		ListNode prev = fakeHead;
		int res = 0;
		while (l1 != null || l2 != null) {
			int val1 = l1 == null ? 0 : l1.val;
			int val2 = l2 == null ? 0 : l2.val;
			int tmp = val1 + val2 + res;
			ListNode node = new ListNode(tmp % 10);
			res = tmp / 10;
			prev.next = node;
			prev = node;
			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}
		if(res!=0) {
			ListNode node = new ListNode(res);
			prev.next = node;
		}
		return fakeHead.next;
	}

	@Test
	public void test0() {
		int[] nums1 = new int[]{2,4,3};
		int[] nums2 = new int[]{5,6,4};
		PrettyPrint.print(addTwoNumbers(ListNode.build(nums1), ListNode.build(nums2)));
	}
}
