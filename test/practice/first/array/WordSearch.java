package practice.first.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (word.length() == 0 || board.length == 0 || board[0].length == 0)
            return false;
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]==chars[0] && search(i, j, 0, chars, board)) return true;
            }
        }
        return false;

    }

    private boolean search(int i, int j, int index, char[] chars, char[][] board) {
        if (index==chars.length)
            return true;
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != chars[index])
            return false;
        board[i][j] ^= 256;
        boolean res = search(i - 1, j, index+1, chars, board) ||
                search(i + 1, j, index+1, chars, board) ||
                search(i, j + 1, index+1, chars, board) ||
                search(i, j - 1, index+1, chars, board);
        if(res) return true;
        board[i][j] ^= 256;
        return false;
    }

    @Test
    public void test0() {
        char[][] board = new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        //assertTrue(exist(board, "ABCCED"));
        assertTrue(exist(board, "SEE"));
        assertFalse(exist(board, "ABCB"));
    }

    @Test
    public void test1() {
        char[][] board = new char[][] {{'A', 'A'}};
        assertFalse(exist(board, "AAA"));
    }
}
