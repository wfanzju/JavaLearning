package leetcode;

import helper.TreeNode;

/**
 * Created by fan on 9/21/15.
 */
public class MinMaxDepthOfBT {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null) {
            return minDepth(root.getRight()) + 1;
        } else if (root.getRight() == null) {
            return minDepth(root.getLeft()) + 1;
        }
        return Math.min(minDepth(root.getLeft()), minDepth(root.getRight())) + 1;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight())) + 1;
    }
}
