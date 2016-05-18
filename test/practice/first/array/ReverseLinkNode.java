package practice.first.array;

import org.junit.Test;

import practice.first.util.ListNode;
import practice.first.util.PrettyPrint;

public class ReverseLinkNode {
    
    public ListNode reverse(ListNode node) {
        ListNode pre = null;
        while(node!=null) {
            ListNode tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
        }
        return pre;
    }
    
    @Test
    public void test0() {
        ListNode root = ListNode.build(new int[]{1,2,3,4,5});
        PrettyPrint.print(reverse(root));
    }

}
