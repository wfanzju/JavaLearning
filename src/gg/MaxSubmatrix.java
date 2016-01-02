package gg;

/**
 * Created by fan on 10/31/15.
 */
public class MaxSubmatrix {
    public int getMaxArea(int[][] nums, int budget) {
        int m = nums.length;
        int n = nums[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            int[] sumArr = new int[n];
            for (int j = i; j < m; j++) {
                addToArr(sumArr, nums[j]);
                int maxLen = findLongestSubArray(sumArr, budget);
                maxArea = Math.max(maxArea, maxLen * (j - i + 1));
            }
        }
        return maxArea;
    }

    private int findLongestSubArray(int[] nums, int budget) {
        int maxLen = 0;
        int start = 0;
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (currSum > budget) {
                maxLen = Math.max(maxLen, i - start);
            }
            while (currSum > budget) {
                currSum -= nums[start++];
            }
        }
        return maxLen == 0 ? nums.length : maxLen;
    }

    private void addToArr(int[] sum, int[] a) {
        for (int i = 0; i < sum.length; i++) {
            sum[i] += a[i];
        }
    }
}
