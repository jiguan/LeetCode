package practice.first.util;

import java.util.List;

public class PrettyPrint {
	static public void print(ListNode node) {
		while(node!=null) {
			System.out.print(node.val+"->");
			node = node.next;
		}
		System.out.println("null");
	}
	
	static public void print(int[] arr) {
		System.out.print("[");
		for(int num : arr) {
			System.out.print(num+", ");
		}
		System.out.println("]");
	}
	
	static public void print(List<Integer> arr) {
		System.out.print("[");
		for(int num : arr) {
			System.out.print(num+", ");
		}
		System.out.println("]");
	}
}
