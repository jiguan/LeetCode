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

    static public void print(List<Integer> arr) {
        System.out.print("[");
        for (int num : arr) {
            System.out.print(num + ", ");
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
