package com.interview.indeed;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

class UnrolledLinkedList {
    Node head = new Node();
    int totalLen = 0;

    public char get(int index) {
        if (index >= totalLen) {
            return ' ';
        }
        Node node = head;
        // index range for current node [0, len)
        // if index >= len, move to next
        while (node != null && index >= node.len) {
            index -= node.len;
            node = node.next;
        }
        if (node == null) {
            return ' ';
        }
        return node.chars[index];
    }

    public void insert(char ch, int index) {

        if (index > totalLen) {
            return;
        }
        Node node = head;
        
        // index can be [0, len] if there is room
        // however if capacity == index, move to next
        while (index > node.len || node.chars.length == index) {
            index -= node.len;
            if (node.next == null) {
                node.next = new Node();
            }
            node = node.next;
        }

        // still has some room
        if (node.len < node.chars.length) {
            add(node, index, ch);
        } else {
            // current node is full, add to next node
            if (node.next != null && node.next.len < node.next.chars.length) {
                add(node.next, 0, node.chars[node.chars.length - 1]);
                add(node, index, ch);
            } else {
                Node newNode = new Node();
                add(newNode, 0, ch);
                Node next = node.next;
                node.next = newNode;
                newNode.next = next;
            }
        }
        totalLen++;
    }

    void add(Node node, int index, char ch) {
        for (int i = Math.min(node.len, node.chars.length - 1); i > index; i--) {
            node.chars[i] = node.chars[i - 1];
        }
        node.chars[index] = ch;
        node.len++;
    }

    public void delete(int index) {
        if (index < 0 || index >= totalLen)
            return;

        Node prev = new Node();
        prev.next = head;
        Node node = head;
        // delete index range [0, len)
        while (node != null && index >= node.len) {
            index -= node.len;
            prev = node;
            node = node.next;
        }

        node.chars[index] = ' ';
        for (int i = index; i < node.len - 1; i++) {
            node.chars[i] = node.chars[i + 1];
        }
        node.chars[node.len - 1] = ' ';
        node.len--;
        if (node.len == 0 && node != head) {
            prev.next = node.next;
        }
        totalLen--;
    }

    class Node {
        char[] chars = new char[5];
        int len;
        Node next;

        @Override
        public String toString() {
            return String.valueOf(this.chars);
        }
    }
}


public class UnrolledLinkedListImpl {

    @Test
    public void test0() {
        UnrolledLinkedList list = new UnrolledLinkedList();
        list.insert('a', 0);
        print(list);
        list.delete(0);
        print(list);
    }

    @Test
    public void test1() {
        UnrolledLinkedList list = new UnrolledLinkedList();
        list.insert('a', 0);
        list.insert('b', 1);
        list.insert('c', 2);
        list.insert('d', 3);
        list.insert('e', 4);
        list.insert('f', 5);
        print(list);
        list.insert('$', 5);
        print(list);
        list.delete(4);
        print(list);
        list.delete(4);
        print(list);
        list.delete(4);
        print(list);
    }

    @Test
    public void test2() {
        UnrolledLinkedList list = new UnrolledLinkedList();
        list.insert('a', 0);
        list.head.next = list.new Node();
        list.add(list.head.next, 0, 'b');
        list.head.next.next = list.new Node();
        list.add(list.head.next.next, 0, 'c');
        list.totalLen = 3;
        print(list);
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('c', list.get(2));

        list.insert('$', 2);
        print(list);
        list.delete(2);
        print(list);
        list.delete(2);
        print(list);
    }

    private String print(UnrolledLinkedList list) {
        UnrolledLinkedList.Node node = list.head;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append("[");
            sb.append(String.valueOf(node.chars));
            sb.append("]->");
            node = node.next;
        }
        sb.append(node);
        String res = sb.toString();
        System.out.println(res);
        return res;
    }

}

