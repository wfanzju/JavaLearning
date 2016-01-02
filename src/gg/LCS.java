package gg;

/**
 * Created by fan on 11/7/15.
 */
public class LCS {
    public static int longestCommonSubstring(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] lcs = new int[m + 1][n + 1];
        int maxLen = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    lcs[i + 1][j + 1] = lcs[i][j] + 1;
                    maxLen = Math.max(maxLen, lcs[i + 1][j + 1]);
                } else {
                    lcs[i + 1][j + 1] = 0;
                }
            }
        }
        return maxLen;
    }

    public static int longestCommonSubsequence(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    lcs[i + 1][j + 1] = lcs[i][j] + 1;
                } else {
                    lcs[i + 1][j + 1] = Math.max(lcs[i][j + 1], lcs[i + 1][j]);
                }
            }
        }
        return lcs[m][n];
    }
}
