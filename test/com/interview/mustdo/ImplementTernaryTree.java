package com.interview.mustdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class ImplementTernaryTree {
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

    public int getHeight(TernaryNode node) {
        if (node == null) {
            return -1;
        } else {
            return 1 + Math.max(Math.max(getHeight(node.left), getHeight(node.right)), getHeight(node.mid));
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
    // https://github.com/tylermccreary/Ternary-Tree/blob/master/TernaryTree.java
    public void print(TernaryNode root) {
        Queue<TernaryNode> queue = new LinkedList<>();
        queue.add(root);
        int total = getHeight(root);
        int curr = 0;
        while (curr <= total) {
            int size = queue.size();
            int offset = calcOffset(curr++, total);
            System.out.print(CharBuffer.allocate(offset).toString().replace('\0', ' '));
            for (int i = 0; i < size; ++i) {
                TernaryNode node = queue.poll();
                if (node == null) {
                    System.out.print("X");
                    queue.add(null);
                } else {
                    System.out.print(node.val);
                    queue.add(node.left);
                    queue.add(node.mid);
                    queue.add(node.right);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void testInsert() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 5, 4, 9, 5, 7, 2, 2);
        String s = tree.toString();
        System.out.println(s);
        assertTrue(s.equals("5422597"));
        tree.delete(9);
        s = tree.toString();
        assertTrue(s.equals("542257"));
        insertMultiple(tree, 8, 7, 20, 20, 14, 12, 17, 30, 25, 30, 32, 14, 20, 20, 20);
        s = tree.toString();
        assertTrue(s.equals("5422577820141214172020202030253032"));
    }

    @Test
    public void testDuplicates() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 2, 2, 1, 3, 2, 1, 3);
        String s = tree.toString();
        assertTrue(s.equals("2112233"));
        deleteMultiple(tree, 2, 1, 2);
        s = tree.toString();
        assertTrue(s.equals("2133"));
    }

    @Test
    public void testRootReplacement() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 20, 20, 10, 30, 5, 10, 15, 30, 25, 40);
        String s = tree.toString();
        assertTrue(s.equals("2010510152030253040"));
        tree.delete(20);
        s = tree.toString();
        assertTrue(s.equals("20105101530253040"));
        tree.delete(20);
        s = tree.toString();
        assertEquals("151051030253040", s);
        deleteMultiple(tree, 10, 10, 5);
        tree.delete(15);
        s = tree.toString();
        assertEquals("30253040", s);
    }

    @Test
    public void testReplaceWithCenter() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 5, 4, 9, 5, 7, 9, 2, 2, 10);
        tree.delete(9);
        String s = tree.toString();
        assertTrue(s.equals("542259710"));
    }

    @Test
    public void testReplaceWithLeft() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 5, 4, 9, 5, 7, 2, 2, 10);
        tree.delete(9);
        String s = tree.toString();
        assertTrue(s.equals("54225710"));
    }

    @Test
    public void testReplaceWithRight() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 5, 4, 9, 5, 2, 2, 10, 12);
        tree.delete(9);
        String s = tree.toString();
        assertTrue(s.equals("542251012"));
    }

    @Test
    public void testRightMost() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 20, 10, 5, 10, 15, 12, 15, 18, 30, 25, 30, 40);
        String s = tree.toString();
        assertTrue(s.equals("20105101512151830253040"));
        tree.delete(20);
        s = tree.toString();
        assertTrue(s.equals("181051015121530253040"));
        tree.delete(18);
        s = tree.toString();
        assertTrue(s.equals("1510510121530253040"));
        tree.delete(15);
        s = tree.toString();
        assertTrue(s.equals("15105101230253040"));
        tree.delete(15);
        s = tree.toString();
        assertTrue(s.equals("121051030253040"));
        tree.delete(12);
        s = tree.toString();
        assertEquals("1051030253040", s);
    }

    @Test
    public void testDeleteLeaf() {
        TernaryTree tree = new TernaryTree();
        insertMultiple(tree, 20, 10, 5, 10, 15, 12, 15, 18, 30, 25, 30, 40);
        String s = tree.toString();
        assertTrue(s.equals("20105101512151830253040"));
        tree.delete(40);
        s = tree.toString();
        assertTrue(s.equals("201051015121518302530"));
    }

    private void insertMultiple(TernaryTree tree, Integer... integers) {
        for (int i = 0; i < integers.length; i++) {
            tree.insert(integers[i]);
        }
    }

    private void deleteMultiple(TernaryTree tree, Integer... integers) {
        for (int i = 0; i < integers.length; i++) {
            tree.delete(integers[i]);
        }
    }
}

class TernaryTree {
    public TernaryNode root;

    public void insert(int val) {
        // create new root if tree is empty
        if (root == null) {
            root = new TernaryNode(val);
        } else {
            insert(root, new TernaryNode(val));
        }
    }

    private void insert(TernaryNode node, TernaryNode newNode) {
        if (newNode == null) {
            return;
        }
        if (newNode.val < node.val) {
            if (node.left == null) {
                node.left = newNode;
            } else {
                insert(node.left, newNode);
            }
        } else if (newNode.val > node.val) {
            if (node.right == null) {
                node.right = newNode;
            } else {
                insert(node.right, newNode);
            }
        } else {
            if (node.mid == null) {
                node.mid = newNode;
            } else {
                insert(node.mid, newNode);
            }
        }
    }

    public void delete(int val) {
        root = delete(root, val);
    }

    private TernaryNode delete(TernaryNode node, int val) {
        if (node == null) return null;
        if (node.val < val) {
            node.right = delete(node.right, val);
        } else if (node.val > val) {
            node.left = delete(node.left, val);
        } else if (node.val == val) {
            if (node.mid != null) {
                node.mid = delete(node.mid, val);
            } else {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else { // both left and right are not null
                    node.val = getMax(node.left).val;
                    node.left = delete(node.left, node.val);
                    // check any node from the left subtree has the same value, if so, move it under node.middle
                    TernaryNode leftMax = getMax(node.left);
                    while (leftMax != null && node.val == leftMax.val) {
                        TernaryNode tmp = node;
                        while(tmp.mid != null) {
                            tmp = tmp.mid;
                        }
                        tmp.mid = new TernaryNode(node.val);
                        node.left = delete(node.left, node.val);
                        leftMax = getMax(node.left);
                    }
                }
            }
        }
        return node;
    }

    private TernaryNode getMax(TernaryNode node) {
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
        }
        return node;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(TernaryNode root) {
        String result = "";
        if (root == null) {
            return "null";
        } else {
            result = Integer.toString(root.val);
            if (root.left != null || root.mid != null || root.right != null) {
                if (root.left != null) {
                    result = result + toString(root.left);
                }
                if (root.mid != null) {
                    result = result + toString(root.mid);
                }
                if (root.right != null) {
                    result = result + toString(root.right);
                }
            }
        }
        return result;
    }
}

class TernaryNode {
    TernaryNode left, mid, right;
    int val;
    TernaryNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
