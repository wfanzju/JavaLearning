package gg;

/**
 * Created by fan on 10/28/15.
 */
public class RegexMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*' && j > 0) {
                dp[0][j + 1] = dp[0][j - 1];
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*' && j > 0) {
                    // bac and ba*c, bc and ba*c
                    if (dp[i + 1][j] || dp[i + 1][j - 1]) {
                        dp[i + 1][j + 1] = true;
                        // baaac and ba*c
                    } else if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = dp[i][j + 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean matchDP(String s, String p) {
        // dp[i][j] : match between s[0:i-1] and p[0:j-1]
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            dp[0][j + 1] = (p.charAt(j) == '*') && dp[0][j];
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        RegexMatching test = new RegexMatching();
        System.out.println(test.matchDP("youtube", "*t*e"));
    }
}
