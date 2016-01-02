package leetcode;

/**
 * Created by fan on 11/5/15.
 */
public class LIS {
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLen = 1;
        int[] tailTable = new int[nums.length];
        tailTable[0] = nums[0];
        int[] preIndices = new int[nums.length];
        int[] tailIndices = new int[nums.length];
        tailIndices[0] = 0;
        preIndices[0] = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tailTable[maxLen - 1]) {
                preIndices[i] = tailIndices[maxLen - 1];
                tailIndices[maxLen] = i;
                tailTable[maxLen++] = nums[i];
            } else {
                int pos = findCeil(tailTable, 0, maxLen - 1, nums[i]);
                preIndices[i] = tailIndices[pos - 1];
                tailIndices[pos] = i;
                tailTable[pos] = nums[i];
            }
        }
        for (int i = tailIndices[maxLen - 1]; i >= 0; i = preIndices[i]) {
            System.out.println(nums[i]);
        }
        return maxLen;
    }

    private static int findCeil(int[] nums, int l, int r, int target) {
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        lengthOfLIS(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
    }
}
