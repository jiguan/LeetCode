package com.interview.pinintest;
import java.util.ArrayList;
import java.util.List;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Treasure {
  
  public static List<int[]> position(int[][] board, int startI, int startJ) {
    int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int m = board.length, n = board[0].length;
    List<int[]> res = new ArrayList<>();
    for(int[] d: dir) {
      int i = startI + d[0];
      int j = startJ + d[1];
      if(i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 0) {
        res.add(new int[]{i,j});
      }
    }
    return res;
  }
  
  public static boolean connected(int[][] board, int endI, int endJ) {
    int m = board.length, n = board[0].length;
    int[][] boardCopy = new int[m][n];
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        boardCopy[i][j] = board[i][j];
      }
    }
    boolean connected = false;
    for(int i = 0;i < m; i++){
      for(int j = 0; j < n; j++){
        if(boardCopy[i][j] == 0) {
          if(!connected) {
            connected = true;
            dfs(boardCopy, i, j);
          } else {
            return false;
          }
        
      }
    }
    }
    return true;
    
  }

  public static void dfs(int[][] board, int startI, int startJ) {
    int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int m = board.length, n = board[0].length;
    
    board[startI][startJ]= 1;
    
    for(int[] d: dir) {
      int i = startI + d[0];
      int j = startJ + d[1];
      if(i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 0) {
        dfs(board, i, j);
      }
    }
    
  }
  
  public static void dfs2(int[][] board, int i, int j, int endI, int endJ, int count, int totalTreasure, List<int[]> cur, List<List<int[]>> res) {
    int m = board.length, n = board[0].length;
    int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    
    if(i >= 0 && i < m && j >= 0 && j < n) {
      if(board[i][j] == 1 || board[i][j] == 0) {

        //cur.add(new int[]{i,j});
        if(board[i][j] == 1){
          count++;
        }
        int temp = board[i][j];
        board[i][j] = 2;
        if( i == endI && j == endJ && count == totalTreasure) {
          res.add(new ArrayList<>(cur));
          cur.remove(cur.size()-1);
          board[i][j] = temp;
          return;
        }
        for(int[] d:dir) {
          int newi = i + d[0];
          int newj = j + d[1];
          dfs2(board, newi, newj, endI, endJ, count, totalTreasure, cur, res);
        }
        board[i][j] = temp;
        cur.remove(cur.size() -1);
      }
    }
  }
  
  public static List<int[]> treasure(int[][] board, int startI, int startJ, int endI, int endJ) {
    int m = board.length, n = board[0].length;
    int totalTreasure = 0;
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        if(board[i][j] == 1) {
          totalTreasure++;
        }
      }
    }
    int[][] boardCopy = new int[m][n];
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        boardCopy[i][j] = board[i][j];
      }
    }
    List<List<int[]>> paths = new ArrayList<>();
    dfs2(boardCopy, startI, startJ, endI, endJ, 0, totalTreasure, new ArrayList<>(), paths);
    List<int[]> shortest = paths.get(0);
    for(int i = 1; i < paths.size(); i++) {
      if(paths.get(i).size() < shortest.size()) {
        shortest = paths.get(i);
      }
    }
    return shortest;
  }
         
  
              
  
  public static void printList(List<int[]> res1) {
    for(int[] array: res1) {
      System.out.println(array[0] +" " + array[1]);
    }
  }
  
  public static void main(String[] args) {
    int[][] board = new int[][] {
      { 0,  0,  0, 0, -1 },
      { 0, -1, -1, 0,  0 },
      { 0,  0,  0, 0,  0 },
      { 0, -1,  0, 0,  0 },
      { 0,  0,  0, 0,  0 },
      { 0,  0,  0, 0,  0 },
    };
    int[][] board2 = new int[][] {
      {  0,  0,  0, 0, -1 },
      {  0, -1, -1, 0,  0 },
      {  0,  0,  0, 0,  0 },
      {  0, -1,  0, 0,  0 },
      { -1,  0,  0, 0,  0 },
      {  0, -1,  0, 0,  0 },
    };
    int[][] board3 = new int[][] {
      {  1,  0,  0, 0,  0 },
      {  0, -1, -1, 0, 0 },
      {  0, -1,  0, 1, 0 },
      {  -1,  0,  0, 0, 0 },
      { 0,  1, -1, 0, 0 },
      {  0,  0,  0, 0, 0 },
    };


    int[] start = new int[] { 5, 0 };
    int[] end = new int[] { 0, 4 };

    
    List<int[]> res1 = position(board, start[0], start[1]);
    
    List<int[]> res2 = position(board, 5, 4);
    
    //printList(res2);
    
    // System.out.println(connected(board2, 0, 0)); 
    // System.out.println(connected(board, 0, 0)); 
    
    List<int[]> res3 = treasure(board3, start[0], start[1], end[0], end[1]);
    
    printList(res3);

  }
}

