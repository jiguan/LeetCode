package practice.first.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// http://codercareer.blogspot.com/2011/12/no-25-edit-distance.html
public class EditDistance {
    public int getDistance(String word1, String word2) {
    	 int m = word1.length(), n = word2.length();
         int[][] mat = new int[m+1][n+1];
         for(int i=1;i<m+1;i++) {
             mat[i][0] = i;
         }
         for(int i=1;i<n+1;i++) {
             mat[0][i] = i;
         } 
         
         for(int i=1;i<=m;i++) {
             for(int j=1;j<=n;j++) {
                 if(word1.charAt(i-1)==word2.charAt(j-1)) {
                     mat[i][j] = mat[i-1][j-1];
                 } else {
                       mat[i][j] = Math.min(Math.min(mat[i-1][j-1], mat[i][j-1]), mat[i-1][j]) + 1;
                 }
             }
         }
         return mat[m][n];
    }
    
    @Test
    public void test0() {
        String s = "Saturday", r = "Sunday";
        assertEquals(3, getDistance(s, r));
    }
    
    @Test
    public void test1() {
        String s = "", r = "";
        assertEquals(0, getDistance(s, r));
    }
    
    @Test
    public void test2() {
        String s = "a", r = "a";
        assertEquals(0, getDistance(s, r));
    }
    
    @Test
    public void test3() {
        String s = "plasma", r = "altruism";
        assertEquals(6, getDistance(s, r));
    }
}
