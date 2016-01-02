package leetcode;

import helper.TreeNode;

/**
 * Created by fan on 11/21/15.
 */
public class ClosestValueInBST {
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = (target < a) ? root.left : root.right;
        if (child == null) {
            return a;
        }
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    public int closestValueIter(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (Math.abs(target - res) > Math.abs(root.val - target)) {
                res = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return res;
    }
}
