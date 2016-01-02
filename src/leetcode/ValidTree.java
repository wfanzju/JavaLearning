package leetcode;

import java.util.Arrays;

/**
 * Created by fan on 10/27/15.
 */
public class ValidTree {
    public boolean validTree(int n, int[][] edges) {
        int[] nums = new int[n];
        Arrays.fill(nums, -1);

        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);
            if (x == y) {
                return false;
            }
            nums[x] = y;
        }
        return edges.length == n - 1;
    }

    private int find(int[] nums, int i) {
        return nums[i] == -1 ? i : find(nums, nums[i]);
    }
}
