package practice.first.util;

public class ListNode {
	public int val;
	public ListNode next;
	public ListNode(int x) {
		val = x;
	}
	@Override
	public String toString() {
		return String.valueOf(val);
	}
}