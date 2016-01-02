package leetcode;

import java.util.PriorityQueue;

/**
 * Created by fan on 7/27/15.
 */
public class KthLargest {

    // O(k) space, O(n log k) time
    public int findKthLargest(int[] num, int k) {
        final PriorityQueue<Integer> minPq = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            minPq.offer(num[i]);
        }
        for (int i = k; i < num.length; i++) {
            if (num[i] > minPq.peek()) {
                minPq.poll();
                minPq.offer(num[i]);
            }
        }
        return minPq.peek();
    }

    public int findKthSmallest(int[] num, int k) {
        final PriorityQueue<Integer> maxPq = new PriorityQueue<>(k, (x, y) -> y.compareTo(x));
        for (int i = 0; i < k; i++) {
            maxPq.offer(num[i]);
        }
        for (int i = k; i < num.length; i++) {
            if (num[i] < maxPq.peek()) {
                maxPq.poll();
                maxPq.offer(num[i]);
            }
        }
        return maxPq.peek();
    }

    public static void main(String[] args) {
        int[] num = new int[]{0, 1, 2, 4, 3, 5};
        KthLargest test = new KthLargest();
        System.out.println(test.findKthLargest(num, 3));
        System.out.println(test.findKthSmallest(num, 3));
    }
}
