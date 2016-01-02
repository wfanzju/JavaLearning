package leetcode;

import helper.TreeNode;

import java.util.List;

/**
 * Created by fan on 10/25/15.
 */
public class DiameterOfBT {
    public int diameterOfBT(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lheight = heightOfBT(root.getLeft());
        int rheight = heightOfBT(root.getRight());

        int ldiameter = diameterOfBT(root.getLeft());
        int rdiameter = diameterOfBT(root.getRight());

        return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));
    }

    private int heightOfBT(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightOfBT(root.getLeft()),
                heightOfBT(root.getRight()));
    }
}
