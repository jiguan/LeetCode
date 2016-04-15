package practice.first.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// http://codercareer.blogspot.com/2011/12/no-25-edit-distance.html
public class EditDistance {
    public int getDistance(String s, String r) {
        int[][] distance = new int[s.length()+1][r.length()+1];
        for (int i = 0; i <= s.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= r.length(); j++) {
            distance[0][j] = j;
        }
        
        for(int i=1;i<=s.length();i++) {
            for(int j=1;j<=r.length();j++) {
                if(s.charAt(i-1)==r.charAt(j-1)) {
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    distance[i][j] = 1 + Math.min(Math.min(distance[i-1][j], distance[i][j-1]), distance[i-1][j-1]);;
                }
            }
        }
        
        return distance[s.length()][r.length()];
    }
    
    @Test
    public void test0() {
        String s = "Saturday", r = "Sunday";
        assertEquals(3, getDistance(s, r));
    }
}
