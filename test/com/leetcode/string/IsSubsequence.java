package com.leetcode.string;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if(s.isEmpty()) return true;
        char[] s_arr = s.toCharArray(), t_arr = t.toCharArray();
        
        int index = 0;
        for(int i = 0; i < t_arr.length && index < s_arr.length;++i) {
            if(s_arr[index] == t_arr[i]) {
                ++index;
            }
        }
        return index == s.length();
    }

    @Test
    public void test0() {
        String s = "abc", t = "ahbgdc";
        assertTrue(isSubsequence(s, t));
    }
    
    @Test
    public void test1() {
        long res = 1 ;
        for(int i =1 ;i<9;++i) {
            res = res * i;
        }
        System.out.println(res);
    }
}
