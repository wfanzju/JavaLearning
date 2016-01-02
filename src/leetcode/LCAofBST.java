package leetcode;

import helper.TreeNode;

/**
 * Created by fan on 9/4/15.
 */
public class LCAofBST {

    public TreeNode lcaRec(TreeNode root, TreeNode p, TreeNode q) {
        if (root.getVal() < p.getVal() && root.getVal() < q.getVal()) {
            return lcaRec(root.getRight(), p, q);
        } else if (root.getVal() > p.getVal() && root.getVal() > q.getVal()) {
            return lcaRec(root.getLeft(), p, q);
        } else {
            return root;
        }
    }

    public TreeNode lcaIter(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.getVal() < p.getVal() && root.getVal() < q.getVal()) {
                root = root.getRight();
            } else if (root.getVal() > p.getVal() && root.getVal() > q.getVal()) {
                root = root.getLeft();
            } else {
                break;
            }
        }
        return root;
    }
}
