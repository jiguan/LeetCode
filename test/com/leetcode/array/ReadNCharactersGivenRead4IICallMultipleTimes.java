package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ReadNCharactersGivenRead4IICallMultipleTimes {

    @Test
    public void test0() {
        Reader read = new Reader("abc");
        char[] buf = new char[4];
        assertEquals(3, read.read(buf, 4));
        assertEquals('a', buf[0]);
        assertEquals('b', buf[1]);
        assertEquals('c', buf[2]);
    }
}

class Reader extends Reader4 {
    Reader(String content) {
        super(content);
    }

    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    public int read(char[] buf, int n) {
        int pointer = 0;
        while (pointer < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (pointer < n && buffPtr < buffCnt) {
                buf[pointer++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return pointer;
    }
}


class Reader4 {
    int index = 0;
    String content = "";

    Reader4(String content) {
        this.content = content;
    }

    int read4(char[] buf) {
        int res = Math.min(index + 4, content.length()) - index;
        for (int i = 0; i < res; ++i) {
            buf[i] = content.charAt(i + index);
        }
        index += res;
        return res;
    }
}


