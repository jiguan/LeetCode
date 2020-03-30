package com.leetcode.linkednodes;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.Test;
import com.leetcode.util.ListNode;

public class NextGreaterNodeInLinkedList {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next) {
            list.add(node.val);
        }
        int[] res = new int[list.size()];
        // storing index
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < list.size(); ++i) {
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)) {
                res[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        return res;
    }

    public int[] nextLargerNodesUsingMap(ListNode head) {
        // num - next large num
        Map<Integer, List<Integer>> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            while (!stack.isEmpty() && stack.peek() < node.val) {
                int prev = stack.pop();
                List<Integer> list = map.getOrDefault(prev, new LinkedList<>());
                list.add(node.val);
                map.put(prev, list);
            }
            stack.add(node.val);
            node = node.next;
        }

        List<Integer> res = new ArrayList<>();
        node = head;
        while (node != null) {
            if (map.containsKey(node.val) && !map.get(node.val).isEmpty()) {
                res.add(map.get(node.val).remove(0));
            } else {
                res.add(0);
            }
            node = node.next;
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            arr[i] = res.get(i);
        }

        return arr;
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build(new int[] {1, 7, 5, 1, 9, 2, 5, 1});
        int[] expected = new int[] {7, 9, 9, 9, 0, 5, 0, 0};
        assertArrayEquals(expected, nextLargerNodes(head));
    }
}
