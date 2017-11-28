package com.leetcode.linkednodes;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class ReverseLinkNode {
    
    public ListNode reverse(ListNode node) {
        ListNode pre = null;
        while(node!=null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
    
    @Test
    public void test() {
        ListNode root = ListNode.build(new int[]{1,2,3,4,5});
        PrettyPrint.print(reverse(root));
    }

}
