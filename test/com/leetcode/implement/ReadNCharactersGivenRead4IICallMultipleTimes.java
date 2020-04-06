package com.leetcode.implement;

import com.leetcode.util.Reader4;

public class ReadNCharactersGivenRead4IICallMultipleTimes {

}


class Solution extends Reader4 {
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    /**
     * @param buf Destination buffer
     * @param n Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            // all chars in buff used up, set pointer to 0
            if (buffPtr == buffCnt) buffPtr = 0;
            // read4 returns less than 4, end of file
            if (buffCnt < 4) break;
        }
        return ptr;
    }
}
