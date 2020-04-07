package com.interview.phone.atlassian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Test;

public class IteratorForCombination {

    @Test
    public void test0() {
        Node root = new Node(new ArrayList<>());
        root.lists.add(new Node(new ArrayList<>()));
        root.lists.get(0).lists.add(new Node(0));
        root.lists.get(0).lists.add(new Node(new ArrayList<>()));
        root.lists.get(0).lists.get(1).lists.add(new Node(1));
        root.lists.get(0).lists.get(1).lists.add(new Node(2));
        root.lists.add(new Node(3));

        NodeIterator iter = new NodeIterator(root);
        assertTrue(iter.hasNext());
        assertEquals(Integer.valueOf(0), Integer.valueOf(iter.next()));
        assertEquals(Integer.valueOf(1), Integer.valueOf(iter.next()));
        assertEquals(Integer.valueOf(2), Integer.valueOf(iter.next()));
        assertEquals(Integer.valueOf(3), Integer.valueOf(iter.next()));
        assertFalse(iter.hasNext());
    }

}


class NodeIterator {
    Queue<Integer> queue = new LinkedList<>();

    NodeIterator(Node node) {
        parse(node);
    }

    private void parse(Node root) {
        if (root == null) return;
        if (root.val != null) {
            queue.add(root.val);
        } else if (root.lists != null) {
            for (int i = 0; i < root.lists.size(); ++i) {
                parse(root.lists.get(i));
            }
        }
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public int next() {
        return queue.poll();
    }
}

class Node {
    List<Node> lists;
    Integer val;

    Node(int val) {
        this.val = val;
    }

    Node(List<Node> lists) {
        this.lists = lists;
    }
}
