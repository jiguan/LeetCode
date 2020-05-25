package com.leetcode.linkednodes;

import java.util.Stack;
import org.junit.Test;
import com.leetcode.util.ImmutableListNode;

/*
 * Print Immutable Linked List in Reverse
 * 
 * You are given an immutable linked list, print out all values of each node in reverse with the
 * help of the following interface:
 * 
 * ImmutableListNode: An interface of immutable linked list, you are given the head of the list. You
 * need to use the following functions to access the linked list (you can't access the
 * ImmutableListNode directly):
 * 
 * ImmutableListNode.printValue(): Print value of the current node. ImmutableListNode.getNext():
 * Return the next node. The input is only given to initialize the linked list internally. You must
 * solve this problem without modifying the linked list. In other words, you must operate the linked
 * list using only the mentioned APIs.
 * 
 * Follow up:
 * 
 * Could you solve this problem in:
 * 
 * Constant space complexity? Linear time complexity and less than linear space complexity?
 * 
 */

public class PrintImmutableLinkedListInReverse {

    /**
    @formatter:off
    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head == null) {
            return;
        }
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }
    @formatter:on
    */

    public void printLinkedListInReverse(ImmutableListNode head) {
        Stack<ImmutableListNode> groupHead = new Stack<>();
        Stack<ImmutableListNode> workingStack = new Stack<>();
        int len = 0;
        ImmutableListNode tmp = head;

        while (tmp != null) {
            tmp = tmp.getNext();
            len++;
        }

        int groupSize = (int) Math.sqrt(len) + 1;

        tmp = head;

        for (int i = 0; i < groupSize; i++) {
            if (i % groupSize == 0) {
                groupHead.push(tmp);
            }
            tmp = tmp.getNext();
        }

        ImmutableListNode start = null, end = null;

        while (!groupHead.isEmpty()) {
            end = start;
            start = groupHead.pop();
            tmp = start;
            
            // since groupHead is a stack, previous start is current end
            while (tmp != end) {
                workingStack.push(tmp);
                tmp = tmp.getNext();
            }

            while (!workingStack.isEmpty()) {
                workingStack.pop().printValue();
            }
        }
    }

    @Test
    public void test0() {
        int[] nums = {-2, 0, 6, 4, 4, -6};
        ImmutableListNode head = ImmutableListNode.build(nums);
        printLinkedListInReverse(head);
    }
}
