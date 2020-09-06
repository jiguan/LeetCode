package com.interview.oci;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;

public class DeserializeTree {

    @Test
    public void test0() {
        String input = "1,2,3,#,#,4,5,#,#,#,#";
        Node root = deserialize(input);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.right);
        System.out.println(root.left.left);
        System.out.println(root.left.right);
        System.out.println(root.right.left);
        System.out.println(root.right.right);
    }

    @Test
    public void test1() {
        String input = "1,2,#,#,3,4,#,#,5,#,#";
        Queue<String> queue = new LinkedList<>(Arrays.asList(input.split(",")));
        Node root = deserialize2(queue);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.right);
        System.out.println(root.left.left);
        System.out.println(root.left.right);
        System.out.println(root.right.left);
        System.out.println(root.right.right);
    }

    
    public Node deserialize2(Queue<String> queue) {
        if(queue.isEmpty()) {
            return null;
        }
        String val = queue.poll();
        if(val.equals("#")) {
            return null;
        }
        Node root = new Node(val);
        root.left = deserialize2(queue);
        root.right = deserialize2(queue);
        return root;
    }
    
    
    public String serialize(Node root) {
        if (root == null)
            return "#";
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                sb.append("#,");
            } else {
                sb.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }

        }
        // remove last ','
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public Node deserialize(String input) {
        if (input.equals("#"))
            return null;
        String[] vals = input.split(",");
        Node root = new Node(vals[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int left = 1, right = 2;
        while(right < vals.length) {
            Node node = queue.poll();
            if (!vals[left].equals("#")) {
                node.left = new Node(vals[left]);
                queue.add(node.left);
            }

            if (!vals[right].equals("#")) {
                node.right = new Node(vals[right]);
                queue.add(node.right);
            }
            left += 2;
            right += 2;
        }
        return root;
    }
}



class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
    }

    Node(String val) {
        this.val = Integer.valueOf(val);
    }

    public String toString() {
        return this.val + "";
    }

}
