package leetcode;

/**
 * Created by fan on 11/11/15.
 */
public class UglyNumber {
    public static int nthUglyNumber(int n) {
        int[] nums = new int[n];
        int t2 = 0, t3 = 0, t5 = 0;
        nums[0] = 1;
        for (int i = 1; i < n; i++) {
            nums[i] = Math.min(nums[t2] * 2, Math.min(nums[t3] * 3, nums[t5] * 5));
            if (nums[i] == nums[t2] * 2) {
                t2++;
            } else if (nums[i] == nums[t3] * 3) {
                t3++;
            } else if (nums[i] == nums[t5] * 5) {
                t5++;
            }
        }
        return nums[n - 1];
    }
}
