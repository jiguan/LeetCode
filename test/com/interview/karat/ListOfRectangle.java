package com.interview.karat;

import java.util.ArrayList;
import java.util.List;

/*
 * 1. there is an image filled with 0s and 1s. There is at most one rectangle in this image filled
 * with 0s, find the rectangle. Output could be the coordinates of top-left and bottom-right
 * elements of the rectangle, or top-left element, width and height.
 * 
 * 2. for the same image, it is filled with 0s and 1s. It may have multiple rectangles filled with
 * 0s. The rectangles are separated by 1s. Find all the rectangles.
 * 
 * 3. the image has random shapes filled with 0s, separated by 1s. Find all the shapes. Each shape
 * is represented by coordinates of all the elements inside.
 */
public class ListOfRectangle {

    public int[] rectangle1(int[][] matrix) {
        int[] res = new int[4];
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    int iRight = i, jDown = j;
                    while (iRight < row) {
                        if (matrix[iRight][j] == 0) {
                            iRight++;
                        } else {
                            break;
                        }
                    }
                    while (jDown < col) {
                        if (matrix[i][jDown] == 0) {
                            jDown++;
                        } else {
                            break;
                        }
                    }
                    return new int[] {i, j, iRight - 1, jDown - 1};
                }
            }
        }
        return res;
    }

    // multiple valid rectangles
    public List<int[]> rectangleMulti(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {

                    int iRight = i, jDown = j;
                    while (iRight < row) {
                        if (matrix[iRight][j] == 0) {
                            matrix[iRight][j] = 1;
                            iRight++;
                        } else {
                            break;
                        }
                    }

                    while (jDown < col) {
                        if (matrix[i][jDown] == 0) {
                            matrix[i][jDown] = 1;
                            jDown++;
                        } else {
                            break;
                        }
                    }
                    
                    for(int a = i; a < iRight; ++a) {
                        for(int b = j; j < jDown; ++b) {
                            matrix[a][b] = 1;
                        }
                    }
                    
                    res.add(new int[] {i, j, iRight - 1, jDown - 1});
                }
            }
        }
        return res;
    }

}
