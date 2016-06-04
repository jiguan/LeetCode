package practice.first.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PrettyPrint {
	static public void print(ListNode node) {
		while (node != null) {
			System.out.print(node.val + "->");
			node = node.next;
		}
		System.out.println("null");
	}

	static public void print(int[] arr) {
		System.out.print("[");
		for (int num : arr) {
			System.out.print(num + ", ");
		}
		System.out.println("]");
	}

	static public void print(String[] arr) {
		System.out.print("[");
		for (String str : arr) {
			System.out.print(str + ", ");
		}
		System.out.println("]");
	}
	
	static public void print(char[][] chars) {
		System.out.print("[");
		for (int i=0;i<chars.length;i++) {
			char[] arr = chars[i];
			if(i==0) {
				System.out.print("[");
			} else {
				System.out.print(" [");
			}
			for(char c : arr) {
				System.out.print(c + " ");
			}
			if(i==chars.length-1) {
				System.out.println("]]");
			} else {
				System.out.println("]");
			}
		}

	}

	static public <T> void print(List<T> list) {
		if(list==null) {
			System.out.println("null");
			return;
		}
		System.out.print("[");
		for (T element : list) {
			System.out.print(element + ", ");
		}
		System.out.println("]");
	}

	static public void print(TreeNode root) {
		BTreePrinter.printNode(root);
	}

	static public <K, V> void print(Map<K, V> map) {
		System.out.print(Arrays.toString(map.entrySet().toArray()));
	}

}
