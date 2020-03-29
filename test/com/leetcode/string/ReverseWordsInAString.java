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

    public String reverseWords1(String s) {
        StringBuilder sb = new StringBuilder();

        int end = s.length() - 1;
        while (end >= 0) {
            while (end >= 0 && s.charAt(end) == ' ') {
                --end;
            }
            int start = end - 1;
            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }
            if (start + 1 >= 0) {
                sb.append(" ");
                sb.append(s.substring(start + 1, end + 1));
            }
            end = start - 1;

        }
        if (sb.length() > 0) sb.deleteCharAt(0);
        return sb.toString();

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

    @Test
    public void test4() {
        String s = "  hello world!  ";
        assertEquals("world! hello", reverseWords1(s));
    }
    
    @Test
    public void test5() {
        String s = "a b";
        assertEquals("b a", reverseWords1(s));
    }
}
