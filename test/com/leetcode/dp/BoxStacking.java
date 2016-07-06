package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

// http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
public class BoxStacking {
    List<Box> boxes = new ArrayList<>();
    int[] height;
    int max = 0;
    
    public int build(int[][] arr) {
        int len = arr.length;
        
        for (int i = 0; i < arr.length; i++) {
            int[] box = arr[i];
            for(Box b : new Box(box[0], box[1], box[2]).rotate()) {
                boxes.add(b);
            }
        }
        
        Collections.sort(boxes);
        
        len = boxes.size();
        height = new int[len];
        for (int i = 0; i < len; i++ )
            height[i] = boxes.get(i).height; //height is for stack height if use i box
        
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                Box prevBase = boxes.get(j);
                Box currentBox = boxes.get(i);
                if(prevBase.width > currentBox.width && prevBase.length > currentBox.length) {
                    height[i] = Math.max(height[j]+currentBox.height, height[i]);
                    if(height[i] > max) max = height[i];
                }
            }
        }
        return max;
    }

    @Test
    public void test0() {
        int[][] boxes = new int[][] {{4, 6, 7}, {1, 2, 3}, {4, 5, 6}, {10, 12, 32}};
        assertEquals(60, build(boxes));
    }


}


class Box implements Comparable<Box> {
    int height, width, length;

    public Box(int length, int width, int height) {
        super();
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public Box[] rotate() {
        Box[] boxes = new Box[3];
        boxes[0] = new Box(this.height, this.width, this.length);
        boxes[1] = new Box(this.length, this.height, this.width);
        boxes[2] = new Box(this.width, this.length, this.height);
        return boxes;
    }

    @Override
    public String toString() {
        return length + " x " + width + " x " + height;
    }

    public int area() {
        return this.length * this.width;
    }


    //decreasing order
    @Override
    public int compareTo(Box anotherBox) {
        return  anotherBox.area() - this.area() ;
    }

}
