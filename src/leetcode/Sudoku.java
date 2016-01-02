package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 10/25/15.
 */
public class Sudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isPartialValid(board, i, i + 1, 0, 9) ||
                    !isPartialValid(board, 0, 9, i, i + 1)) {
                return false;
            }
            int x = (i / 3) * 3;
            int y = (i % 3) * 3;
            if (!isPartialValid(board, x, x + 3, y, y + 3)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPartialValid(char[][] board, int x1, int x2, int y1, int y2) {
        Set<Character> set = new HashSet<>();
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if (board[i][j] != '.' && !set.add(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solveDFS(board);
    }

    private boolean solveDFS(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveDFS(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int i, int j, char c) {
        int x = i / 3;
        int y = j / 3;
        for (int k = 0; k < 9; k++) {
            if (board[k][j] == c ||
                    board[i][k] == c ||
                    board[x * 3 + k / 3][y * 3 + k % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
