package leetcode;

/**
 * Created by fan on 8/2/15.
 */
public class MaximalSquare {

    public int dp(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int maxLen = 0;
        int[][] maxTable = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            maxTable[i][0] = matrix[i][0] == '0' ? 0 : 1;
            maxLen = Math.max(maxLen, maxTable[i][0]);
        }
        for (int i = 1; i < matrix[0].length; i++) {
            maxTable[0][i] = matrix[0][i] == '0' ? 0 : 1;
            maxLen = Math.max(maxLen, maxTable[0][i]);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                maxTable[i][j] = matrix[i][j] == '0'
                        ? 0
                        : Math.min(
                        Math.min(maxTable[i - 1][j], maxTable[i][j - 1]),
                        maxTable[i - 1][j - 1]) + 1;
                maxLen = Math.max(maxLen, maxTable[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}