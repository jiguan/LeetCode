package com.hackerank.test;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class DecodeTheMessage {
    private String encode(String text) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            System.out.println(Integer.valueOf(c));
            char newChar = c += c + i;
            System.out.println(Integer.valueOf(newChar));
            b.append(newChar);
        }
        return b.reverse().toString();
    }

    public String decode(String encodedMessage) {
        StringBuffer buffer = new StringBuffer(encodedMessage);
        buffer.reverse();
        
        StringBuffer res = new StringBuffer();
        for(int i = 0 ; i<buffer.length(); i++) {
            res.append((char)((buffer.charAt(i) - i) / 2));
        }
        return res.toString();
    }

    @Test
    public void test0() {
        String encodeMessage = encode("This is mssage");
        String msg = decode(encodeMessage);
        System.out.println(msg);
    }
}
