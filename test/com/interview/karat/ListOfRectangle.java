package com.interview.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    static List<int[]> findMultiRectangle(int[][] matrix) {
        List<int[]> res = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        int[][] arr = matrix.clone();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr[i][j] == 0) {
                    int[] curr = new int[4];
                    curr[0] = i;
                    curr[1] = j;

                    int tmp_i = i;

                    while (tmp_i + 1 < m && matrix[tmp_i + 1][j] == 0) {
                        ++tmp_i;
                    }
                    curr[2] = tmp_i;

                    int tmp_j = j;
                    while (tmp_j + 1 < n && matrix[i][tmp_j + 1] == 0) {
                        ++tmp_j;
                    }
                    curr[3] = tmp_j;
                    res.add(curr);
                    fill(arr, curr);
                }
            }
        }
        return res;
    }

    static List<List<int[]>> findAllRectangle(int[][] matrix) {

        List<List<int[]>> res = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        int[][] arr = matrix.clone();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr[i][j] == 0) {
                    List<int[]> cur = new LinkedList<>();
                    dfs(arr, i, j, cur);
                    res.add(new ArrayList<>(cur));
                }
            }
        }
        return res;
    }

    static void dfs(int[][] arr, int i, int j, List<int[]> cur) {
        if (i >= arr.length || i < 0 || j >= arr[0].length || j < 0 || arr[i][j] == 1) return;

        int[][] ds = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        arr[i][j] = 1;
        cur.add(new int[] {i, j});

        for (int[] d : ds) {
            dfs(arr, i + d[0], j + d[1], cur);
        }
    }

    static void fill(int[][] arr, int[] cur) {
        int i = cur[0], j = cur[1], m = cur[2], n = cur[3];
        for (int a = i; a <= m; a++) {
            for (int b = j; b <= n; ++b) {
                arr[a][b] = 1;
            }
        }
    }

    static void print(List<int[]> list) {
        for (int[] val : list) {
            System.out.print(Arrays.toString(val));
        }
    }

}
