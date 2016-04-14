package practice.first.array;

import java.util.Stack;

import org.junit.Test;

public class ImplementQueueUsingStacks {
	
	
	@Test
	public void test0() {
		MyQueue2 queue = new MyQueue2();
		int[] nums = new int[]{1,2,3,4};
		for(int num : nums) {
			queue.push(num);
		}
		while(!queue.empty()) {
			System.out.print(queue.peek());
			queue.pop();
		}
		
	}
}

class MyQueue2 {
	Stack<Integer> stack = new Stack<Integer>();
	public void push(int x) {
		Stack<Integer> tmp = new Stack<Integer>();
		while(!stack.empty()) {
			tmp.push(stack.pop());
		}
		tmp.push(x);
		while(!tmp.isEmpty()) {
			stack.push(tmp.pop());
		}
	}

	// Removes the element from in front of queue.
	public void pop() {
		stack.pop();
	}

	// Get the front element.
	public int peek() {
		return stack.peek();
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return stack.isEmpty();
	}
	
}


class MyQueue1{
	Stack<Integer> main = new Stack<Integer>();
	Stack<Integer> backup = new Stack<Integer>();
	
	public void push(int x) {
		backup.push(x);
	}

	// Removes the element from in front of queue.
	public void pop() {
		check();
		main.pop();
	}

	// Get the front element.
	public int peek() {
		check();
		return main.peek();
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return main.isEmpty() && backup.isEmpty();
	}
	
	private void check() {
		if(main.isEmpty()) {
			while(!backup.isEmpty()) {
				main.push(backup.pop());
			}
		}
	}
	
}
