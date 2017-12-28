package com.leetcode.linkednodes;

import org.junit.Test;

import com.leetcode.util.ListNode;
import com.leetcode.util.PrettyPrint;

public class ReverseLinkedListII {
    public ListNode reverseBetween0(ListNode head, int m, int n) {
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode pre = fake;
        for (int i = 0; i < m - 1; ++i) {
            pre = pre.next;
        }

        ListNode start = pre.next; // beginning of the sub-list
        ListNode node = start.next; // node is going to be reversed

        // start is fixed on its node, always the last one
        for (int i = 0; i < n - m; ++i) {
            start.next = node.next;
            node.next = pre.next;
            pre.next = node;
            node = start.next;
         }

        return fake.next;
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) return head;
        ListNode first = null, second = null;
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode prefirst = null;;
        ListNode pre = fake, node = head;
        for(int i = 0;i<n;++i) {
            if(i == m - 1) {
            	prefirst = pre;
                first = node;
            }
            pre = pre.next;
            node = node.next;
        }

        for(int i = 0;i<n-m;++i){
        	prefirst.next = first.next;
            first.next = pre;
            pre.next = first;
            first = prefirst.next;
        }
        
        return fake.next;
    }

    @Test
    public void test0() {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        // Expect 1->4->3->2-5
        PrettyPrint.print(reverseBetween(head, 2, 4));
    }
}
