package practice.first.array;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import practice.first.util.ListNode;

public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        if (node.next.next == null) {
            node.val = node.next.val;
            node.next = null;
        } else {
            node.val = node.next.val;
            deleteNode(node.next);
        }
    }

    @Test
    public void test0() {
        ListNode root = new ListNode(-1);
        ListNode node1 = new ListNode(1);
        root.next = node1;
        deleteNode(root);
        assertEquals(1, root.val);
        assertNull(root.next);
    }
    
    @Test
    public void test1() {
        ListNode root = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(0);
        root.next = node1;
        node1.next = node2;
        deleteNode(root);
        assertEquals(1, root.val);
        assertEquals(0, node1.val);
        assertNull(node1.next);
    }
    
    
    public int reverse(int x) {
        boolean neg = false;
        long longX = x;
        if(x<0) {
            neg = true;
            longX = -longX;
        }
        char[] arr = (longX+"").toCharArray();
        for(int i=0, j=arr.length-1;i<j;i++,j--) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        long tmp  = (neg ? -1 : 1 ) * Long.valueOf(new String(arr));
        if(tmp>Integer.MAX_VALUE||tmp<Integer.MIN_VALUE) return 0;
        return (int)tmp;
    }
    
    @Test
    public void test2() {
        int x = -2147483648;
        reverse(x);
    }
    
}
