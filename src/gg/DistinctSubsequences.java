package gg;

import java.util.Arrays;

/**
 * Created by fan on 11/1/15.
 */
public class DistinctSubsequences {
    public static int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[m + 1][n + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (t.charAt(i) == s.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i + 1][j];
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
