package com.leetcode.linkednodes;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CopyListWithRandomPointer {
    /*
     * The idea is to associate the original node with its copy node in a single linked list. In this way, we don't need
     * extra space to keep track of the new nodes.
     * 
     * The algorithm is composed of the follow three steps which are also 3 iteration rounds.
     * 
     * 1) Iterate the original list and duplicate each node. The duplicate of each node follows its original
     * immediately. 2) Iterate the new list and assign the random pointer for each duplicated node. 3) Restore the
     * original list and extract the duplicated nodes.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode iter = head;

        // make a copy of each node
        while(iter!=null) {
            RandomListNode next = iter.next;
            RandomListNode node = new RandomListNode(iter.label);
            iter.next = node;
            node.next = next;
            iter = next;
        }
        
        // assign random pointer for the newly created node
        iter = head;
        while(iter!=null) {
            if(iter.random !=null) {
                // copy's random = random's copy
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }
        
        // restore the linked list
        iter = head;
        RandomListNode fake = new RandomListNode(0);
        RandomListNode node = fake;
        
        while(iter!=null) {
            RandomListNode copy = iter.next;
            node.next = copy;
            node = copy;
            
            iter.next = iter.next.next;
            iter = iter.next;
        }
        return fake.next;
    }

    public RandomListNode copyRandomList0(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        return copy(head, map);
    }

    private RandomListNode copy(RandomListNode node, Map<RandomListNode, RandomListNode> map) {
        if (map.containsKey(node)) return map.get(node);

        RandomListNode newNode = new RandomListNode(node.label);
        map.put(node, newNode);
        if (node.next != null) newNode.next = copy(node.next, map);
        if (node.random != null) newNode.random = copy(node.random, map);

        return newNode;
    }

    @Test
    public void test() {
        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.random = head.next.next;
        head.next.random = head.next;
        head.next.next.random = head;

        RandomListNode.PrettyPrint(copyRandomList(head));
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) {
        this.label = x;
    }

    static void PrettyPrint(RandomListNode node) {
        while (node != null) {
            System.out.println(node.label + "->" + (node.random == null ? "null" : node.random.label));
            System.out.println("|");
            System.out.println("V");
            node = node.next;
        }
        System.out.println("null");
    }
};