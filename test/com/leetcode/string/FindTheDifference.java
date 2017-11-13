package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FindTheDifference {
    public char findTheDifference(String s, String t) {
    	long charcode = t.charAt(s.length());
    	for(int i=0; i<s.length(); ++i) {
    		charcode += s.charAt(i);
    		charcode -= t.charAt(i);
    	}
        return (char)charcode;
    }
    
    @Test
    public void test0() {
    	String s = "abcd", t = "abcde";
    	char expect = 'e';
    	
    	assertEquals(expect, findTheDifference(s, t));
    }
}
