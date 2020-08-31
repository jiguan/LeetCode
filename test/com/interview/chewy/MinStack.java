package com.interview.chewy;

public class MinStack {

}


class Stack {

    Node tail = null;

    public void push(int x) {
        if (tail == null) {
            tail = new Node(x, x);
        } else {
            Node node = new Node(x, Math.min(x, tail.min));
            node.prev = tail;
            tail = node;
        }
    }

    public void pop() {
        if (tail != null) {
            tail = tail.prev;
        }
    }

    public int top() {
        return tail == null ? -1 : tail.val;
    }

    public int getMin() {
        return tail == null ? -1 : tail.min;
    }
}


class Node {
    int val;
    int min;
    Node prev;

    Node(int val, int min) {
        this.val = val;
        this.min = min;
    }
}
