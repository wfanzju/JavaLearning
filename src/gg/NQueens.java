package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 10/22/15.
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queenPos = new int[n];
        dfs(res, queenPos, 0);
        return res;
    }

    private void dfs(List<List<String>> res, int[] queenPos, int row) {
        int n = queenPos.length;
        if (row == n) {
            printBoard(res, queenPos);
        } else {
            for (int col = 0; col < n; col++) {
                queenPos[row] = col;
                if (isValid(queenPos, row)) {
                    dfs(res, queenPos, row + 1);
                }
            }
        }
    }

    private boolean isValid(int[] queenPos, int row) {
        for (int i = 0; i < row; i++) {
            if (queenPos[i] == queenPos[row] ||
                    row - i == Math.abs(queenPos[i] - queenPos[row])) {
                return false;
            }
        }
        return true;
    }

    private void printBoard(List<List<String>> res, int[] queenPos) {
        int n = queenPos.length;
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = "";
            for (int j = 0; j < n; j++) {
                if (j == queenPos[i]) {
                    line += "Q";
                } else {
                    line += ".";
                }
            }
            board.add(line);
        }
        res.add(board);
    }
}
