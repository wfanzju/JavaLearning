package leetcode;

import helper.TreeNode;

/**
 * Created by fan on 9/18/15.
 */
public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        return validRec(root, null, null);
    }

    private boolean validRec(TreeNode t, Integer low, Integer high) {
        if (t == null) {
            return true;
        }
        return (low == null || t.getVal() > low)
                && (high == null || t.getVal() < high)
                && validRec(t.getLeft(), low, t.getVal())
                && validRec(t.getRight(), high, t.getVal());
    }
}
