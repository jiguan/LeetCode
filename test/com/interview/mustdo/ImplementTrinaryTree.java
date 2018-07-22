package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class ImplementTrinaryTree {
    // Question 2) Implement insert and delete in a tri-nary tree. A tri-nary tree is much like a binary
    // tree but with three child nodes for each parent instead of two -- with the left node being values
    // less than the parent, the right node values greater than the parent, and the middle nodes values
    // equal to the parent.
    // For example, suppose I added the following nodes to the tree in this order: 5, 4, 9, 5, 7, 2, 2.
    // The resulting tree would look like this:
    //@formatter:off
    //     5
    //   / | \
    //   4 5 9
    //  /   /
    // 2   7
    // |
    // 2
   //@formatter:on

    public int getHeight(Node node) {
        if (node == null) {
            return -1;
        } else {
            return 1 + Math.max(Math.max(getHeight(node.left), getHeight(node.right)), getHeight(node.middle));
        }
    }

    @Test
    public void testHeight1() {
        assertEquals(0, calcOffset(1, 1));
    }

    @Test
    public void testHeight2() {
        assertEquals(4, calcOffset(0, 2));
        assertEquals(3, calcOffset(1, 2));
        assertEquals(0, calcOffset(2, 2));
    }

    @Test
    public void testHeight3() {
        assertEquals(13, calcOffset(0, 3));
        assertEquals(12, calcOffset(1, 3));
        assertEquals(9, calcOffset(2, 3));
        assertEquals(0, calcOffset(3, 3));
    }

    public int calcOffset(int currLevel, int totalLevel) {
        int res = 0;
        for (int i = currLevel; i < totalLevel; ++i) {
            res += Math.pow(3, i);
        }
        return res;
    }

    public void print(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int total = getHeight(root);
        int curr = 0;
        while (curr <= total) {
            int size = queue.size();
            int offset = calcOffset(curr++, total);
            System.out.print(CharBuffer.allocate(offset).toString().replace('\0', ' '));
            for (int i = 0; i < size; ++i) {
                Node node = queue.poll();
                if (node == null) {
                    System.out.print("X");
                    queue.add(null);
                } else {
                    System.out.print(node.val);
                    queue.add(node.left);
                    queue.add(node.middle);
                    queue.add(node.right);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void test0() {
        TrinaryTree tree = new TrinaryTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(9);
        tree.insert(5);
        tree.insert(7);
        tree.insert(2);
        tree.insert(2);
        tree.insert(11);
        tree.insert(11);

        print(tree.root);
        tree.delete(9);
        print(tree.root);
    }
}

// Refer https://gist.github.com/iowaguy/f46fe575feaec082fc80
class TrinaryTree {
    public Node root;

    public void insert(int val) {
        // create new root if tree is empty
        if (root == null) {
            root = new Node(val);
        } else {
            insert(root, new Node(val));
        }
    }

    private void insert(Node root, Node newNode) {
        if (newNode == null) {
            return;
        }
        if (newNode.val < root.val) {
            if (root.left == null) {
                root.left = newNode;
            } else {
                insert(root.left, newNode);
            }
        } else if (newNode.val > root.val) {
            if (root.right == null) {
                root.right = newNode;
            } else {
                insert(root.right, newNode);
            }
        } else {
            if (root.middle == null) {
                root.middle = newNode;
            } else {
                insert(root.middle, newNode);
            }
        }

    }
    public void delete(int val) {
        delete(root, val);
    }

    private Node delete(Node node, int val) {
        if (node == null) return null;
        if (node.val < val) {
            node.right = delete(node.right, val);
        } else if (node.val > val) {
            node.left = delete(node.left, val);
        } else {
            if (node.middle != null) {
                node.middle = delete(node.middle, val);
            } else {
                if (node.left == null && node.right == null) {
                    return null;
                } else if (node.right != null) {
                    node.val = getMin(node.right).val;
                    Node tmp = delete(node.right, getMin(node.right).val);
                    node.right = null;
                    if (node.val == tmp.val) {
                        node.middle = tmp;
                    } else {
                        node.right = tmp;
                    }
                } else {
                    node = node.left;
                }
            }
        }
        return node;
    }

    private Node getMin(Node node) {
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
        }
        return node;
    }
}

class Node {
    Node left, middle, right;
    int val;
    Node(int val) {
        this.val = val;
    }
}
