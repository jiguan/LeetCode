package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.leetcode.util.PrettyPrint;

public class DesignCompressedStringIterator {
    @Test
    public void test1() {
        String a = "1b1";
        // head is not ignore, tail is ignored
        String[] strs = a.split("1");
        PrettyPrint.print(strs);
    }

    @Test
    public void test0() {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

        assertEquals('L', iterator.next()); // return 'L'
        assertEquals('e', iterator.next()); // return 'e'
        assertEquals('e', iterator.next()); // return 'e'
        assertEquals('t', iterator.next()); // return 't'
        assertEquals('C', iterator.next()); // return 'C'
        assertEquals('o', iterator.next()); // return 'o'
        assertEquals('d', iterator.next()); // return 'd'
        assertTrue(iterator.hasNext()); // return true
        assertEquals('e', iterator.next()); // return 'e'
        assertFalse(iterator.hasNext()); // return false
        assertEquals(' ', iterator.next()); // return ' '
    }
}


class StringIterator {
    String str;
    int index = 0;
    int prevCount = 0;
    char prevLetter = ' ';

    public StringIterator(String compressedString) {
        str = compressedString;
    }

    public char next() {
        if (prevCount == 0) {
            if (index == str.length()) return ' ';
            prevLetter = str.charAt(index);
            int i = index + 1;
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                ++i;
            }
            prevCount = Integer.parseInt(str.substring(index + 1, i));
            index = i;
        }
        prevCount--;
        return prevLetter;
    }

    public boolean hasNext() {
        return !(index == str.length() && prevCount == 0);
    }
}
