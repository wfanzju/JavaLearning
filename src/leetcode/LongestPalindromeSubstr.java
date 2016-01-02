package leetcode;

/**
 * Created by fan on 11/19/15.
 */
public class LongestPalindromeSubstr {
    // O(n^2)
    public String longestPalSubstr(String s) {
        int maxLen = 0;
        String res = "";
        for (int i = 1; i < s.length(); i++) {
            String s1 = longestPalSubstr(s, i - 1, i);
            String s2 = longestPalSubstr(s, i - 1, i + 1);
            for (String str : new String[]{s1, s2}) {
                if (str.length() > maxLen) {
                    maxLen = str.length();
                    res = str;
                }
            }
        }
        return res;
    }

    private String longestPalSubstr(String s, int left, int right) {
        int len = s.length();
        int maxLen = 1;
        int start = left;
        while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }
            left--;
            right++;
        }
        return s.substring(start, start + maxLen);
    }
}
