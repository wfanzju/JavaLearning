package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/14/15.
 */
public class CoinPickingGame {
    // S(i, j) = Max( Vi + min(S(i+2, j), S(i+1, j-1)),
    //                Vj + min(S(i+1, j-1), S(i, j-2))
    // Base Cases:
    // S(i, i) = Vi
    // S(i, j) = Max(Vi, Vj) if j = i + 1
    public static int maxSumOfPlayer(int[] coins) {
        // len must be even
        int len = coins.length;
        int[][] dp = new int[len][len];
        int[][] pick = new int[len][len];
        // fill top-right table in diagonal fashion, finally get dp[0][n-1]
        for (int gap = 0; gap < len; gap++) {
            for (int i = 0; i < len - gap; i++) {
                int j = i + gap;
                if (gap <= 1) {
                    dp[i][j] = Math.max(coins[i], coins[j]);
                    pick[i][j] = (coins[i] >= coins[j]) ? i : j;
                } else {
                    int picki = coins[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                    int pickj = coins[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
                    dp[i][j] = Math.max(picki, pickj);
                    pick[i][j] = (picki >= pickj) ? i : j;
                }
            }
        }
        printMoves(pick, coins);
        return dp[0][len - 1];
    }

    private static void printMoves(int[][] pick, int[] coins) {
        int i = 0;
        int j = coins.length - 1;
        boolean myTurn = true;
        List<Integer> res = new ArrayList<>();
        while (i <= j) {
            if (i == j) {
                if (myTurn) {
                    res.add(i);
                }
                break;
            }
            if (myTurn) {
                res.add(pick[i][j]);
            }
            if (pick[i][j] == i) {
                i++;
            } else {
                j--;
            }
            myTurn = !myTurn;
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{8, 15, 3, 7};
        System.out.println(maxSumOfPlayer(arr1));
        int[] arr2 = new int[]{2, 2, 2, 2};
        System.out.println(maxSumOfPlayer(arr2));
        int[] arr3 = new int[]{20, 30, 2, 2, 2, 10};
        System.out.println(maxSumOfPlayer(arr3));
    }
}
