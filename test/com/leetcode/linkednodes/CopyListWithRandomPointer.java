package com.leetcode.linkednodes;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
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
        while(node!=null) {
            System.out.println(node.label + "->" + (node.random == null ? "null" : node.random.label));
            System.out.println("|");
            System.out.println("V");
            node = node.next;
        }
        System.out.println("null");
    }
};