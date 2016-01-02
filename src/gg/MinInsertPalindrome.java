package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/14/15.
 */
public class MinInsertPalindrome {
    // minInsert(l, r) = minInsert(l+1, r-1)    if s[l]==s[r]
    //                   Min(minInsert(l, r-1), minInsert(l+1, r)) + 1
    public static int minInsert(char[] str, int l, int r) {
        if (l > r) {
            return -1;
        } else if (l == r) {
            return 0;
        } else if (l == r - 1) {
            return str[l] == str[r] ? 0 : 1;
        }
        return str[l] == str[r] ? minInsert(str, l + 1, r - 1)
                : 1 + Math.min(minInsert(str, l, r - 1), minInsert(str, l + 1, r));
    }

    // same with minDelete
    public static int minInsertDp(char[] str) {
        int n = str.length;
        int[][] dp = new int[n][n];
        int[][] insertIdx = new int[n][n];
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0; i < n - gap; i++) {
                int j = i + gap;
                dp[i][j] = (str[i] == str[j]) ? dp[i + 1][j - 1]
                        : 1 + Math.min(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    insertIdx[i][j] = -1;
                } else {
                    insertIdx[i][j] = dp[i][j - 1] <= dp[i + 1][j] ? i : j + 1;
                }
            }
        }
        printInsertIdx(insertIdx);
        return dp[0][n - 1];
    }

    private static void printInsertIdx(int[][] insertIdx) {
        int i = 0;
        int j = insertIdx.length - 1;
        List<Integer> res = new ArrayList<>();
        while (i < j) {
            if (insertIdx[i][j] == -1) {
                i++;
                j--;
                continue;
            }
            res.add(insertIdx[i][j]);
            if (insertIdx[i][j] == i) {
                j--;
            } else {
                i++;
            }
        }
        System.out.println(res);
    }

    public static String shortedPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            if (reversed.substring(i).equals(str.substring(0, n - i))) {
                return reversed.substring(0, i) + str;
            }
        }
        return reversed + str;
    }

    public static void main(String[] args) {
        // System.out.println(shortedPalindrome("dedcba"));
        System.out.println(minInsertDp("abcb".toCharArray()));
    }
}
