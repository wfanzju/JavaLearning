package gg;

/**
 * Created by fan on 11/20/15.
 */
public class RotatedArrSearch {
    public int search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + ((e - s) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[s]) {
                if (target > nums[mid] && target <= nums[e]) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            } else if (nums[mid] > nums[s]) {
                if (target < nums[mid] && target >= nums[s]) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            } else {
                s++;
            }
        }
        return -1;
    }
}
