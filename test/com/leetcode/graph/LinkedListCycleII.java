package com.leetcode.graph;

import com.leetcode.util.ListNode;

public class LinkedListCycleII {

    // @formatter:off
    /*  |<     A      >|<   B   >|
     *  0 -> 1 -> 2 -> 3 -> 4 -> 5
     *                 |         |
     *                 8 <- 7 <- 6
     *  Suppose the distance from the head to the cycle start point is A,
     *  the slow pointer and the fast pointer meets at B inside the cycle.
     *  Then the slow pointer travels A + B, the fast pointer travels 
     *  A + B + N. Since fast pointer is 2 times faster than the slow one, 
     *  then 2A + 2B = A + B + N => N = A + B.
     *  
     *  Right now, slow pointer has traveled B inside the cycle, it can reach
     *  the beginning of the cycle in A steps; meanwhile, after A steps, second
     *  slow pointer will enter the cycle. They will meet at the beginning of 
     *  the cycle.
     */
    // @formatter:on
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }

        }
        return null;
    }
}
