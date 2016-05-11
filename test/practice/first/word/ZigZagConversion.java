package practice.first.word;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if(s==null||s.length()==0||numRows==0) return "";
        numRows = Math.min(s.length(), numRows);
        StringBuffer[] str = new StringBuffer[numRows];
        char[] arr = s.toCharArray();
        int index = -1, flag = 1;
        
        for(char c : arr) {
            if(index==0) {
                index = Math.min(index+1, numRows-1);
                flag = 1;
            } else if(index==numRows-1) {
                flag = -1;
                index = Math.max(index-1, 0);
            } else {
                index+= flag;
            }
            if(str[index]==null) str[index] = new StringBuffer();
            str[index].append(c);
        }
        StringBuffer result = new StringBuffer();
        for(StringBuffer buffer : str) {
            result.append(buffer);
        }
        return result.toString();
    }
    
    @Test
    public void test0() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        assertEquals("PAHNAPLSIIGYIR", convert(s, numRows));
    }
    
    
    @Test
    public void test1() {
        String s = "AB";
        int numRows = 1;
        assertEquals("AB", convert(s, numRows));
    }
    
    @Test
    public void test2() {
        String s = "A";
        int numRows = 2;
        assertEquals("A", convert(s, numRows));
    }
}
