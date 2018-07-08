package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !rows.add(board[i][j])) return false;
                if (board[j][i] != '.' && !columns.add(board[j][i])) return false;
                // check the cube one by one(0, 3) -> (1, 0)
                // cube's location is decided by i
                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                if (board[RowIndex + j / 3][ColIndex + j % 3] != '.'
                        && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
                    return false;
            }
        }
        return true;
    }

    @Test
    public void test0() {
        String[] board_str = new String[]{".87654321", "2........", "3........", "4........", "5........", "6........",
                "7........", "8........", "9........"};
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            String b = board_str[i];
            board[i] = b.toCharArray();
        }
        assertTrue(isValidSudoku(board));
    }

    @Test
    public void test1() {
        String[] board_str = new String[]{"..4...63.", ".........", "5......9.", "...56....", "4.3.....1", "...7.....",
                "...5.....", ".........", "........."};
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            String b = board_str[i];
            board[i] = b.toCharArray();
        }
        assertFalse(isValidSudoku(board));
    }

    @Test
    public void test2() {
        String[] board_str = new String[]{"....5..1.", ".4.3.....", ".....3..1", "8......2.", "..2.7....", ".15......",
                ".....2...", ".2.9.....", "..4......"};
        prettyPrint(board_str);
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            String b = board_str[i];
            board[i] = b.toCharArray();
        }
        assertFalse(isValidSudoku(board));
    }

    private void prettyPrint(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i % 3 == 0) {
                System.out.println("+---+---+---+");
            }
            String s = arr[i];
            char[] chars = s.toCharArray();

            for (int j = 0; j < chars.length; j++) {
                if (j % 3 == 0) {
                    System.out.print("+");
                }
                System.out.print(chars[j]);
            }
            System.out.println("+");
        }
        System.out.println("+---+---+---+");
    }

}
