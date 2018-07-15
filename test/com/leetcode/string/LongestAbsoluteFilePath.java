package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1;
            // find its parent
            while (level + 1 <= stack.size()) {
                stack.pop();
            }
            // length, \t\t\ length, left \
            int len = (stack.isEmpty() ? 0 : stack.peek()) + s.length() - level + 1;
            stack.push(len);
            // check if is a file
            if (s.contains(".")) res = Math.max(res, len - 1);
        }
        return res;
    }

    //@formatter:off
//    dir
//    subdir1
//        file1.ext
//        subsubdir1
//    subdir2
//        subsubdir2
//            file2.ext
    //@formatter:on
    @Test
    public void test0() {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        String expected = String.valueOf("dir/subdir2/subsubdir2/file2.ext");
        assertEquals(expected.length(), lengthLongestPath(input));
    }

    //@formatter:off
//    dir
//    subdir1
//    subdir2
//        file.ext
  //@formatter:on
    @Test
    public void test1() {
        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String expected = String.valueOf("dir/subdir2/file.ext");
        assertEquals(expected.length(), lengthLongestPath(input));
    }
}
