package gg;

/**
 * Created by fan on 11/10/15.
 */
public class MaxSumFromKStacks {
    // max sum picking n numbers from k stacks
    public static int maxSumKStacks(int[][] nums, int n) {
        int k = nums.length;
        // max sum table picking i numbers from first j stacks
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i][j - 1];
                int sum = 0;
                for (int p = 0; p < Math.min(i, nums[j - 1].length); p++) {
                    // use the p+1 nums from the j-th stack
                    sum += nums[j - 1][p];
                    dp[i][j] = Math.max(dp[i][j], sum + dp[i - p - 1][j - 1]);
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 4, 700, 3}, {5, 10, 6}, {10, 4, 30}};
        System.out.println(maxSumKStacks(nums, 6));
    }
}
