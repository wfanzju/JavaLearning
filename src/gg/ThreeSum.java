package gg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fan on 10/31/15.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = num.length - 1;
            int sum = 0 - num[i];
            while (left < right) {
                if (num[left] + num[right] == sum) {
                    res.add(Arrays.asList(num[i], num[left], num[right]));
                    while (left < right && num[left] == num[left + 1]) {
                        left++;
                    }
                    while (left < right && num[right] == num[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (num[left] + num[right] < sum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public int threeSumClosest(int[] num, int target) {
        int res = num[0] + num[1] + num[2];
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1;
            int right = nums.length-1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }

    public List<List<Integer>> kSum(int[] num, int k) {
        Arrays.sort(num);
        return kSumRec(num, k, 0, 0);
    }

    private List<List<Integer>> kSumRec(int[] num, int k, int target, int s) {
        if (k == 2) {
            return twoSum(num, target, s);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = s; i < num.length; i++) {
            if (i > s && num[i] == num[i - 1]) {
                continue;
            }
            List<List<Integer>> k1Sum = kSumRec(num, k - 1, target - num[i], i + 1);
            for (List<Integer> tuple : k1Sum) {
                tuple.add(0, num[i]);
                res.add(tuple);
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] num, int target, int s) {
        List<List<Integer>> res = new ArrayList<>();
        int left = s;
        int right = num.length - 1;
        while (left < right) {
            if (left > s && num[left] == num[left - 1]) {
                left++;
                continue;
            }
            int sum = num[left] + num[right];
            if (sum == target) {
                List<Integer> tuple = new ArrayList<>();
                tuple.add(num[left++]);
                tuple.add(num[right--]);
                res.add(tuple);
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }
}
