package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] dirs = path.split("\\/+");
        Stack<String> stack = new Stack<>();
        for (String dir : dirs) {
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (dir.equals(".") || dir.isEmpty()) {
                continue;
            } else {
                stack.push(dir);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
            sb.insert(0, "/");
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    @Test
    public void test0() {
        String path = "/";
        String expected = "/";
        assertEquals(expected, simplifyPath(path));
    }

    @Test
    public void test1() {
        String path = "/a/./b/../../c/";
        String expected = "/c";
        assertEquals(expected, simplifyPath(path));
    }

    @Test
    public void test4() {
        String path = "/a/./b/../../c";
        String expected = "/c";
        assertEquals(expected, simplifyPath(path));
    }

    @Test
    public void test5() {
        String path = "/home/../../..";
        String expected = "/";
        assertEquals(expected, simplifyPath(path));
    }

    @Test
    public void test6() {
        String path = "///";
        String expected = "/";
        assertEquals(expected, simplifyPath(path));
    }

    @Test
    public void test2() {
        String path = "/home/";
        String expected = "/home";
        assertEquals(expected, simplifyPath(path));
    }

    @Test
    public void test3() {
        String path = "/../";
        String expected = "/";
        assertEquals(expected, simplifyPath(path));
    }
    
    @Test
    public void test7() {
        String path = "/..";
        String expected = "/";
        assertEquals(expected, simplifyPath(path));
    }
}
