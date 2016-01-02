package leetcode;

import helper.TreeNode;

/**
 * Created by fan on 11/1/15.
 */
public class FlattenBTToList {
    // pre-order traversal
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode l = root.getLeft();
        TreeNode r = root.getRight();
        root.setLeft(null);
        flatten(l);
        flatten(r);
        root.setRight(l);
        TreeNode tmp = root;
        while (tmp.getRight() != null) {
            tmp = tmp.getRight();
        }
        tmp.setRight(r);
    }
}
