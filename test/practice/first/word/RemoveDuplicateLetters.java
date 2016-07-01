package practice.first.word;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] letters = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (letters[c - 'a'] != 0) {
                letters[c - 'a'] = Math.min(i + 1, letters[c - 'a']);
            } else {
                letters[c - 'a'] = i + 1;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.insert(letters[i] - 1, 'a' + i);
        }
        return builder.toString();
    }
    
    @Test
    public void test0() {
        String s = "cbacdcbc";
        assertEquals("acdb", removeDuplicateLetters(s));
    }
}
