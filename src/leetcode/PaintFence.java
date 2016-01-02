package leetcode;

/**
 * Created by fan on 10/22/15.
 */
public class PaintFence {
    public int dp(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        int[] sameColor = new int[n];
        int[] diffColor = new int[n];
        sameColor[0] = 0;
        diffColor[0] = k;
        for (int i = 1; i < n; i++) {
            sameColor[i] = diffColor[i - 1];
            diffColor[i] = (k - 1) * (sameColor[i - 1] + diffColor[i - 1]);
        }
        return sameColor[n - 1] + diffColor[n - 1];
    }

    public int constantSpace(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        int preSame = 0;
        int preDiff = k;
        for (int i = 1; i < n; i++) {
            int same = preDiff;
            preDiff = (k - 1) * (preSame + preDiff);
            preSame = same;
        }
        return preSame + preDiff;
    }
}
