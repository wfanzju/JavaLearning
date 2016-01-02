package leetcode;

import java.util.Arrays;

/**
 * Created by fan on 10/25/15.
 */
public class SurroundedRegions {
    public void fillSurrounded(char[][] board) {
        if (board.length < 3 || board[0].length < 3) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                boundaryDFS(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                boundaryDFS(board, i, n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                boundaryDFS(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                boundaryDFS(board, m - 1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void boundaryDFS(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '*';
        }
        if (i > 1 && board[i - 1][j] == 'O') {
            boundaryDFS(board, i - 1, j);
        }
        if (i < board.length - 2 && board[i + 1][j] == 'O') {
            boundaryDFS(board, i + 1, j);
        }
        if (j > 1 && board[i][j - 1] == 'O') {
            boundaryDFS(board, i, j - 1);
        }
        if (j < board[0].length - 2 && board[i][j + 1] == 'O') {
            boundaryDFS(board, i, j + 1);
        }
    }

    public void flipInside(boolean[][] board) {
        if (board.length < 3 || board[0].length < 3) {
            return;
        }
        int n = board.length;
        int m = board[0].length;
        boolean[][] block = new boolean[2][m];
        for (int i = 0; i < 2; i++) {
            block[i] = Arrays.copyOf(board[i], m);
        }
        for (int i = 1; i < n - 1; i++) {
            flipRow(board, block, i);
            block[0] = block[1];
            block[1] = board[i + 1];
        }
    }

    private void flipRow(boolean[][] board, boolean[][] block, int row) {
        for (int i = 1; i < board[0].length - 1; i++) {
            if (block[1][i] == true
                    && block[1][i - 1] == true && block[1][i + 1] == true
                    && block[0][i] == true && board[row + 1][i] == true) {
                board[row][i] = false;
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions test = new SurroundedRegions();
        boolean[][] board = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {true, true, true},
                {true, false, true}};
        test.flipInside(board);
        for (boolean[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
