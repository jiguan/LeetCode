package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

/**
 * Prefix: *+AB-CD (Infix : (A+B) * (C-D) )
 * 
 * Postfix: AB+CD-* (Infix : (A+B * (C-D) )
 *
 */
public class PrefixToPostfixConversion {
    public String convert(String prefix) {
        Set<Character> set = new HashSet<>(Arrays.asList('+', '-', '*', '\\'));
        Stack<Node> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; --i) {
            char c = prefix.charAt(i);
            if (set.contains(c)) {
                Node left = stack.pop();
                Node right = stack.pop();
                Node root = new Node(String.valueOf(c));
                root.left = left;
                root.right = right;
                stack.push(root);
            } else {
                Node node = new Node(String.valueOf(c));
                stack.push(node);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            builder.insert(0, node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return builder.toString();
    }

    class Node {
        Node left, right;
        String val;

        Node(String val) {
            this.val = val;
        }
    }

    @Test
    public void test0() {
        String prefix = "*+AB-CD";
        String postfix = "AB+CD-*";
        assertEquals(postfix, convert(prefix));
    }
}
