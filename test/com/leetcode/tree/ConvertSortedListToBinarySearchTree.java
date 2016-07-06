package com.leetcode.tree;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TreeNode;

public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        return build(head, null);
    }

    private TreeNode build(ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = build(head, slow);
        root.right = build(slow.next, tail);
        return root;
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build(new int[] {1, 2, 3, 4, 5});
        TreeNode result = sortedListToBST(head);
        PrettyPrint.print(result);
    }


}
