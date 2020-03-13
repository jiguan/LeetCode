package com.leetcode.implement;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {

}


class MyStack {
    private Queue<Integer> queue = new LinkedList<>();

    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.poll());
        }
    }

    public void pop() {
        queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
