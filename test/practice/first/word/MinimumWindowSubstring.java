package practice.first.word;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] map = new int[256];
        for (char c : t.toCharArray())
            map[c]++;
        int start = 0;
        int pointer =0, len = Integer.MAX_VALUE;
        int counter = t.length();
        for(int i=0;i<s.length();i++) {
            if(map[s.charAt(i)]-->0) {
                counter--;
            }
            while(counter==0) {
                //always record the pointer, shrink it if not valid
                //pointer now is the correct position for the t char
                if(i - pointer + 1 < len) {
                    start = pointer;
                    len = i - pointer + 1;
                }
                //find next t char to move to the next, make substring invalid
                if(map[s.charAt(pointer++)]++==0) {
                    counter++;
                }
            }
            
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);

    }

    @Test
    public void test0() {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        assertEquals("BANC", minWindow(s, t));
    }

    @Test
    public void test1() {
        String s = "A";
        String t = "AA";

        assertEquals("", minWindow(s, t));
    }
    
    @Test
    public void test2() {
        String s = "AB";
        String t = "B";

        assertEquals("B", minWindow(s, t));
    }
    
}
