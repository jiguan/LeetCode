package com.leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
	private Queue<Integer> queue = new LinkedList<>();
	
	// Push element x onto stack.
	public void push(int x) {
		Queue<Integer> tmp = new LinkedList<>();
		tmp.add(x);
		while(!queue.isEmpty()) {
			tmp.add(queue.poll());
		}
		queue = tmp;
	}

	// Removes the element on top of the stack.
	public void pop() {
		queue.poll();
	}

	// Get the top element.
	public int top() {
		return queue.peek();
	}

	// Return whether the stack is empty.
	public boolean empty() {
		return queue.isEmpty();
	}
}
