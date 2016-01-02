package gg;

import java.util.Arrays;

/**
 * Created by fan on 10/23/15.
 */
public class SearchRange {
    public static int[] searchRange(int[] arr, int target) {
        int start = firstGreaterEqual(arr, target);
        if (start == arr.length || arr[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, firstGreaterEqual(arr, target + 1) - 1};
    }

    private static int firstGreaterEqual(int[] arr, int target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            // low <= mid < high
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] res = searchRange(new int[]{1,2,3,3,3,3,4,5},3);
        System.out.println(Arrays.toString(res));
        System.out.println(firstGreaterEqual(new int[]{1,2,3,4},5));
    }
}
