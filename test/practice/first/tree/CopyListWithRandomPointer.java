package practice.first.tree;

import java.util.HashMap;
import java.util.Map;

import practice.first.util.RandomListNode;

public class CopyListWithRandomPointer {
	//old - new 
	Map<RandomListNode, RandomListNode> map = new HashMap<>(); 

	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;
		if (map.containsKey(head))
			return map.get(head);

		RandomListNode newNode = new RandomListNode(head.label);
		map.put(head, newNode);
		if (head.next != null)
			newNode.next = copyRandomList(head.next);
		if (head.random != null)
			newNode.random = copyRandomList(head.random);

		return newNode;
	}
	

}
