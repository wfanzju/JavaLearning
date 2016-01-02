package gg;


import java.util.Arrays;

/**
 * Created by fan on 11/6/15.
 */
public class LISInMatrix {
    private static class Pos implements Comparable<Pos> {
        int i;
        int j;
        int val;

        Pos(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }

        @Override
        public int compareTo(Pos p) {
            return Integer.compare(this.val, p.val);
        }
    }

    public static int LISInMatrixLen(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // LIS starting from (i,j)
        int[][] lenTable = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(lenTable[i], 1);
        }
        Pos[] ps = new Pos[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ps[i * n + j] = new Pos(i, j, matrix[i][j]);
            }
        }
        Arrays.sort(ps, (p, q) -> -p.compareTo(q));
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int maxLen = 0;
        for (Pos p : ps) {
            int currMax = 0;
            int currI = p.i;
            int currJ = p.j;
            for (int[] d : dirs) {
                int newI = currI + d[0];
                int newJ = currJ + d[1];
                if (newI < 0 || newJ < 0 || newI >= m || newJ >= n) {
                    continue;
                }
                if (matrix[newI][newJ] > p.val) {
                    currMax = Math.max(currMax, lenTable[newI][newJ]);
                }
            }
            currMax += 1;
            lenTable[currI][currJ] = currMax;
            maxLen = Math.max(maxLen, currMax);
        }
        return maxLen;
    }
}
