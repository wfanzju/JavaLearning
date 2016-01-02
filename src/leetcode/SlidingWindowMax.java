package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by fan on 11/21/15.
 */
public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] arr, int k) {
        if (arr == null || k < 1) {
            return null;
        }
        int len = arr.length;
        int[] res = new int[len - k + 1];
        int idx = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (i >= k - 1) {
                res[idx++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }
}
