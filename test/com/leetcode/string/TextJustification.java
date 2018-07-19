package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int start = 0;
        while (start < words.length) {
            int len = words[start].length();
            int end = start + 1;
            while (end < words.length) {
                // if we need to add another word, with its space
                if (words[end].length() + len + 1> maxWidth) break;
                len += 1 + words[end].length();
                end++;
            }

            StringBuilder builder = new StringBuilder();
            // how many space needed in this line, wordNum - 1
            int spaceNum = end - start - 1;

            if (end == words.length || spaceNum == 0) {
                for (int i = start; i < end; ++i) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < maxWidth; ++i) {
                    builder.append(" ");
                }
            } else {
                // space between words
                int space = (maxWidth - len) / spaceNum;
                // if the "word[space]word" cannot fill the whole line, such as "example of text"
                int extra = (maxWidth - len) % spaceNum;
                for (int i = start; i < end; ++i) {
                    builder.append(words[i]);
                    if (i < end - 1) {
                        // when we calculate len, we take one space into consideration, need to add it as well
                        for (int j = 0; j <= (space + ((i - start) < extra ? 1 : 0)); ++j) {
                            builder.append(" ");
                        }
                    }
                }
            }
            res.add(builder.toString());
            start = end;
        }
        return res;
    }

    @Test
    public void test0() {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> expected = Arrays.asList("This    is    an", "example  of text", "justification.  ");
        assertEquals(expected, fullJustify(words, maxWidth));
    }

    @Test
    public void test1() {
        String[] words = {"everything", "else", "we", "do"};
        int maxWidth = 20;
        List<String> expected = Arrays.asList("everything  else  we", "do                  ");
        assertEquals(expected, fullJustify(words, maxWidth));
    }

}
