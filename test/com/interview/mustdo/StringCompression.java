package com.interview.mustdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringCompression {
    public int compress0(char[] chars) {
        int index = 1, count = 1;
        char prev = chars[0];
        for (int i = 1; i <= chars.length; ++i) {
            if (i < chars.length && chars[i] == prev) {
                count++;
            } else {
                if (count != 1) {
                    // count may be 2 digtis
                    int start = index;
                    while (count > 0) {
                        chars[index++] = (char) ('0' + count % 10);
                        count /= 10;
                    }
                    int end = index - 1;
                    swap(chars, start, end);
                }

                if (i < chars.length) {
                    chars[index++] = chars[i];
                    prev = chars[i];
                    count = 1;
                }
            }
        }
        return index;
    }

    public int compress(char[] chars) {
        int i = 0, index = 0;
        while (i < chars.length) {
            char c = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == c) {
                count++;
                i++;
            }
            chars[index++] = c;
            if (count != 1) {
                int start = index;
                while (count > 0) {
                    chars[index++] = (char) ('0' + count % 10);
                    count /= 10;
                }
                int end = index - 1;
                swap(chars, start, end);
            }
        }
        return index;
    }

    private void swap(char[] array, int i, int j) {
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }

    @Test
    public void test0() {
        char[] chars = {'a', 'b', 'c'};
        char[] expected = {'a', 'b', 'c'};
        int len = compress(chars);
        assertEquals(expected.length, len);
        for (int i = 0; i < len; ++i) {
            assertTrue(expected[i] == chars[i]);
        }
    }

    @Test
    public void test1() {
        char[] chars = {'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c'};
        char[] expected = {'a', '2', 'b', '3', 'c', '4'};
        int len = compress(chars);
        assertEquals(expected.length, len);
        for (int i = 0; i < len; ++i) {
            assertTrue(expected[i] == chars[i]);
        }
    }

    @Test
    public void test2() {
        char[] chars = {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'b', 'c'};
        char[] expected = {'a', '1', '2', 'b', 'c'};
        int len = compress(chars);
        assertEquals(expected.length, len);
        for (int i = 0; i < len; ++i) {
            assertTrue(expected[i] == chars[i]);
        }
    }
}
