package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;

public class SimplifyPath {
    public String simplifyPath(String path) {
        LinkedList<String> list = new LinkedList<>();
        Set<String> set = new HashSet<>(Arrays.asList(".", "..", ""));
        for (String str : path.split("/")) {
            if (str.equals("..") && !list.isEmpty()) {
               list.removeLast();
            } else if(!set.contains(str)){
                list.add(str);
            }
        }
        String res = "";
        for(String str : list) {
            res = res + "/" + str;
        }
        if(res.isEmpty()) return "/";
        return res;
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
}
