package com.interview;

/*
 * Create a simple linked list of the following elements ... 5, 11, 3, 79, 36 Remove the second
 * element, then add a new fourth element with value 99. Print the list. What does the final list
 * look like?
 * 
 * Answer: 5, 3, 79, 99, 36
 */
class Solution {
    public static void main(String[] args) {
        Node fakeHead = new Node(-1);
        Node pre = fakeHead;
        int[] arr = {5, 11, 3, 79, 36};
        for (int num : arr) {
            Node node = new Node(num);
            pre.next = node;
            pre = node;
        }
        print(fakeHead.next);

        int removeElement = 2;
        pre = fakeHead;
        for(int i = 1; i< removeElement; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        
        print(fakeHead.next);
        
        int newElementIndex = 4;
        pre = fakeHead;
        for(int i = 1; i< newElementIndex; i++) {
            pre = pre.next;
        }
        Node next = pre.next;
        pre.next = new Node(99);
        pre.next.next = next;
        
        print(fakeHead.next);
    }
    
    private static void print(Node node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

}

class Node {
    Node next;
    int val;

    Node(int val) {
        this.val = val;
    }

}
