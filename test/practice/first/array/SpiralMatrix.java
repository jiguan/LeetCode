package practice.first.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if(matrix.length==0||matrix[0].length==0) return res;
        int[] direction = new int[2]; //x, y direction
        direction[0] = 1;
        int total = matrix.length * matrix[0].length;
        int x = 0, y = 0;
        int x_left = 0, x_right = matrix[0].length-1, y_top = 0, y_bottom = matrix.length-1;
        while(res.size() < total) {
            if(x<x_left) {
                x = x_left;
                direction[0] = 0;
                direction[1] = -1; //go up
                y_bottom -= 1;
                y -=1;
            } else if(x>x_right) {
                x = x_right;
                direction[0] = 0;
                direction[1] = 1;
                y_top += 1;
                y+=1;
            } else if(y<y_top) {
                y = y_top;
                direction[0] = 1;
                x_left += 1;
                direction[1] = 0;
                x+=1;
            } else if(y>y_bottom) {
                y = y_bottom;
                x_right -= 1;
                direction[0] = -1;
                direction[1] = 0;
                x-=1;
            }
            res.add(matrix[y][x]);
            x += direction[0];
            y += direction[1];
         }
        return res;
    }
    
    @Test
    public void test0() {
        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        List<Integer> expected = Arrays.asList(1,2,3,6,9,8,7,4,5);
        assertEquals(spiralOrder(matrix), expected);
    }
    
    @Test
    public void test1() {
        int[][] matrix = new int[][]{{1}};
        List<Integer> expected = Arrays.asList(1);
        assertEquals(spiralOrder(matrix), expected);
    }
}
