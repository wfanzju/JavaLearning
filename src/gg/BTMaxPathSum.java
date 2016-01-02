package gg;

import helper.TreeNode;

/**
 * Created by fan on 11/7/15.
 */
public class BTMaxPathSum {
    int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathDFS(root);
        return maxSum;
    }

    private int maxPathDFS(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, maxPathDFS(node.getLeft()));
        int right = Math.max(0, maxPathDFS(node.getRight()));
        maxSum = Math.max(maxSum, left + right + node.getVal());
        return Math.max(left, right) + node.getVal();
    }
}
