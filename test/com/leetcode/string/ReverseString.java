package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseString {
    public String reverseString(String s) {
        if(s==null||s.length()<2) return s;
        char[] arr = s.toCharArray();
        int len = s.length();
        for(int i=0;i<len/2;i++) {
            char tmp = arr[i];
            arr[i] = arr[len-i-1];
            arr[len-i-1] = tmp;
        }
        return new String(arr);
    }
    @Test
    public void test0() {
        String s = "test";
        assertEquals("tset", reverseString(s));
    }
    
    @Test
    public void test1() {
        String s = "abcde";
        assertEquals("edcba", reverseString(s));
    }
}
