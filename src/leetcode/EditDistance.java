package leetcode;

/**
 * Created by fan on 11/16/15.
 */
public class EditDistance {
    public int minDist(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);
                if (c1 == c2) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = 1 + Math.min(dp[i][j],
                            Math.min(dp[i][j + 1], dp[i + 1][j]));
                }
            }
        }
        return dp[len1][len2];
    }

    public static int minDistOn(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2 + 1];
        for (int i = 0; i < len2 + 1; i++) {
            dp[i] = i;
        }
        for (int i = 0; i < len1; i++) {
            int prev = i + 1;
            for (int j = 0; j < len2; j++) {
                int curr;
                if (word1.charAt(i) == word2.charAt(j)) {
                    curr = dp[j];
                } else {
                    curr = 1 + Math.min(dp[j], Math.min(prev, dp[j + 1]));
                }
                dp[j] = prev;
                prev = curr;
            }
            dp[len2] = prev;
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        System.out.println(minDistOn("b", ""));
    }
}
