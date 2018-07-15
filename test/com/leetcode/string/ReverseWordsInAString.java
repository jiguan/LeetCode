package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        char[] arr = s.toCharArray();
        int n = arr.length;

        // reverse whole string
        reverse(arr, 0, n - 1);
        reverseWord(arr, n);
        return cleanSpaces(arr, n);
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }

    private void reverseWord(char[] arr, int n) {
        int i = 0;
        while (i < n) {
            while (i < n && arr[i] == ' ')
                i++;
            int j = i + 1;
            while (j < n && arr[j] != ' ')
                j++;
            reverse(arr, i, j - 1);
            i = j;
        }
    }

    private String cleanSpaces(char[] arr, int n) {
        int i = 0, j = 0;
        while (j < n) {
            while (j < n && arr[j] == ' ')
                j++;
            while (j < n && arr[j] != ' ')
                arr[i++] = arr[j++];
            // check it again, just in case this is the last word
            while (j < n && arr[j] == ' ')
                j++;
            // this is not, add a space between word
            if (j < n) arr[i++] = ' ';
        }
        return new String(arr).substring(0, i);
    }

    public String reverseWords0(String s) {
        if (s == null || s.length() == 0) return s;
        String[] words = s.trim().split("\\s+");
        StringBuffer buffer = new StringBuffer();
        int len = words.length;
        for (int i = len - 1; i > 0; i--) {
            buffer.append(words[i]);
            buffer.append(" ");
        }
        buffer.append(words[0]);
        return buffer.toString();
    }

    @Test
    public void test0() {
        String s = "the sky is blue";
        assertEquals("blue is sky the", reverseWords(s));
    }

    @Test
    public void test1() {
        String s = " ";
        assertEquals("", reverseWords(s));
    }

    @Test
    public void test2() {
        String s = "   a";
        assertEquals("a", reverseWords(s));
    }
    
    @Test
    public void test3() {
        String s = "   ";
        assertEquals("", reverseWords(s));
    }
}
