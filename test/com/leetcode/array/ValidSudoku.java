package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidSudoku {
   public boolean isValidSudoku(char[][] board) {
      if (board == null || board.length != 9 || board[0].length != 9)
         return false;
      boolean[] row = new boolean[9];
      boolean[][] column = new boolean[9][9];
      boolean[][] part = new boolean[3][9];
      for (int i = 0; i < 9; i++) {
         if (i % 3 == 0) {
            part = new boolean[3][9];
         }
         for (int j = 0; j < 9; j++) {
            char num = board[i][j];
            if (num != '.') {
               boolean f = check(num, part[j / 3]);
               boolean[] l = part[j/3];
               if (!(check(num, row) && check(num, column[j]) && f)) {
                  return false;
               }
            }
         }
         row = new boolean[9];
      }
      return true;
   }


   private boolean check(char num, boolean[] arr) {
      if (arr[num - '1'])
         return false;
      arr[num - '1'] = true;
      return true;
   }
   
   @Test
   public void test0() {
      String[] board_str = new String[]{".87654321","2........","3........","4........","5........","6........","7........","8........","9........"};
      char[][] board = new char[9][9];
      for(int i =0;i<9;i++) {
         String b = board_str[i];
         board[i] = b.toCharArray();
      }
      assertTrue(isValidSudoku(board));
   }
   
   @Test
   public void test1() {
      String[] board_str = new String[]{"..4...63.",".........","5......9.","...56....","4.3.....1","...7.....","...5.....",".........","........."};
      char[][] board = new char[9][9];
      for(int i =0;i<9;i++) {
         String b = board_str[i];
         board[i] = b.toCharArray();
      }
      assertFalse(isValidSudoku(board));
   }
   
   @Test
   public void test2() {
      String[] board_str = new String[]{"....5..1.",".4.3.....",".....3..1","8......2.","..2.7....",".15......",".....2...",".2.9.....","..4......"};
      prettyPrint(board_str);
      char[][] board = new char[9][9];
      for(int i =0;i<9;i++) {
         String b = board_str[i];
         board[i] = b.toCharArray();
      }
      assertFalse(isValidSudoku(board));
   }
   
   private void prettyPrint(String[] arr) {
      for(int i=0;i<arr.length;i++) {
         if(i%3==0) {
            System.out.println("+---+---+---+");
         }
         String s = arr[i];
         char[] chars = s.toCharArray();

         for(int j=0;j<chars.length;j++) {
            if(j%3==0) {
               System.out.print("+");
            }
            System.out.print(chars[j]);
         }
         System.out.println("+");
      }
      System.out.println("+---+---+---+");
   }
   
}
