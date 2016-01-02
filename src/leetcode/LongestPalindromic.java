package leetcode;

/**
 * Created by fan on 10/31/15.
 */
public class LongestPalindromic {
    private int start;
    private int maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        for (int i = 0; i < len - 1; i++) {
            findPalindrome(s, i, i);
            findPalindrome(s, i, i+1);
        }
        return s.substring(start, start + maxLen);
    }

    private void findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {
            start = left+1;
            maxLen = right-left-1;
        }
    }

    public String longestPalindromeDP(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (j - i < 3 || dp[i + 1][j - 1]);
                }
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}
