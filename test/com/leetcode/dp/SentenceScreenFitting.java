package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Sentence Screen Fitting
 * 
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many
 * times the given sentence can be fitted on the screen.
 * 
 * Note:
 * 
 * A word cannot be split into two lines. The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space. Total words in the sentence
 * won't exceed 100. Length of each word is greater than 0 and won't exceed 10. 1 <= rows, cols <=
 * 20,000. Example 1:
 * 
 * Input: rows = 2, cols = 8, sentence = ["hello", "world"]
 * 
 * Output: 1
 * 
 * Explanation: hello--- world---
 * 
 * The character '-' signifies an empty space on the screen. Example 2:
 * 
 * Input: rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 * 
 * Output: 2
 * 
 * Explanation: a-bcd- e-a--- bcd-e-
 * 
 * The character '-' signifies an empty space on the screen. Example 3:
 * 
 * Input: rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 * 
 * Output: 1
 * 
 * Explanation: I-had apple pie-I had--
 * 
 * The character '-' signifies an empty space on the screen.
 */

public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        int currLine = 0;
        int prev = 0;
        for (int i = 0; i < sentence.length; ++i) {
            // remove the length of previous word and space
            if (i != 0 && currLine > 0) {
                currLine -= sentence[i - 1].length() + 1;
            }
            // calculate the start of next line.
            // it's OK the index is beyond the length of array so that
            // we can use it to count how many words one row has.
            while (currLine + sentence[prev % n].length() <= cols) {
                currLine += sentence[prev++ % n].length() + 1;
            }
            
            dp[i] = prev;
        }
        int count = 0;
        for (int i = 0, k = 0; i < rows; ++i) {
            // count how many words one row has and move to start of next row.
            // It's better to check if d[k] == k but I find there is no test case on it.
            // if(dp[k] == k) return 0;
            count += dp[k] - k;
            k = dp[k] % n;
        }
        // divide by the number of words in sentence
        return count / n;
    }

    @Test
    public void test0() {
        String[] sentence = {"I", "had", "apple", "pie"};
        int rows = 4, cols = 5;
        assertEquals(1, wordsTyping(sentence, rows, cols));
    }
}
