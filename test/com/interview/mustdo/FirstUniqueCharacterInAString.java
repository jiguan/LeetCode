package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

// zillow
public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            Character c = s.charAt(i);
            if (set.add(c)) {
                map.put(c, i);
            } else {
                map.remove(c);
            }
        }
        return map.isEmpty() ? -1 : map.entrySet().iterator().next().getValue();
    }

    public int firstUniqChar0(String s) {
        Map<Character, Node> map = new HashMap<>();
        Node head = new Node('0', -1), tail = new Node('0', -1);
        head.next = tail;
        tail.prev = head;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                Node node = map.get(c);
                if (node != null) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                map.put(c, null);
            } else {
                Node node = new Node(c, i);
                Node prev = tail.prev;
                prev.next = node;
                node.prev = prev;
                node.next = tail;
                tail.prev = node;
                map.put(c, node);
            }
        }
        return head.next.index;
    }

    class Node {
        Node prev, next;
        char val;
        int index;

        Node(char val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public String toString() {
            return "[" + val + ", " + index + "]";
        }
    }

    @Test
    public void test0() {
        String s = "leetcode";
        assertEquals(0, firstUniqChar(s));
    }

    @Test
    public void test1() {
        String s = "loveleetcode";
        assertEquals(2, firstUniqChar(s));
    }
}
