package practice.first.word;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        if(s==null||s.length()==0) return s;
        String[] words = s.trim().split("\\s+");
        StringBuffer buffer = new StringBuffer();
        int len = words.length;
        for(int i=len-1;i>0;i--) {
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
}
