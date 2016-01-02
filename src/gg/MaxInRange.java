package gg;

import helper.TreeNode;

/**
 * Created by fan on 11/15/15.
 */
public class MaxInRange {
    TreeNode root;

    public MaxInRange(int[] arr) {
        this.root = buildTree(arr, 0, arr.length - 1);
    }

    private TreeNode buildTree(int[] arr, int s, int e) {
        if (s > e) {
            return null;
        }
        int maxIdx = findMaxIdx(arr, s, e);
        TreeNode root = new TreeNode(maxIdx);
        root.left = buildTree(arr, s, maxIdx - 1);
        root.right = buildTree(arr, maxIdx + 1, e);
        return root;
    }

    private int findMaxIdx(int[] arr, int s, int e) {
        int maxIdx = s;
        while (++s <= e) {
            if (arr[s] > arr[maxIdx]) {
                maxIdx = s;
            }
        }
        return maxIdx;
    }

    public int getMaxIdxWithinRange(int s, int e) {
        return getMaxIdxWithinRange(root, s, e);
    }

    private int getMaxIdxWithinRange(TreeNode root, int s, int e) {
        if (s > e) {
            return -1;
        }
        if (root.val >= s && root.val <= e) {
            return root.val;
        } else if (root.val < s) {
            return getMaxIdxWithinRange(root.right, s, e);
        } else {
            return getMaxIdxWithinRange(root.left, s, e);
        }
    }
}
