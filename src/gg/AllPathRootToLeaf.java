package gg;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 10/10/15.
 */
public class AllPathRootToLeaf {
    public List<List<Integer>> binaryTreePaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfsPath(root, tmp, res);
        return res;
    }

    private void dfsPath(TreeNode root, List<Integer> tmp, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        tmp.add(root.getVal());
        if (root.getLeft() == null && root.getRight() == null) {
            res.add(new ArrayList<>(tmp));
            return;
        } else {
            dfsPath(root.getLeft(), tmp, res);
            dfsPath(root.getRight(), tmp, res);
        }
        tmp.remove(tmp.size() - 1);
    }

    public List<String> bTPaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root != null) {
            dfsPathsString(root, "", res);
        }
        return res;
    }

    private void dfsPathsString(TreeNode root, String tmp, List<String> res) {
        if (root.getLeft() == null && root.getRight() == null) {
            res.add(tmp + root.getVal());
        }
        if (root.getLeft() != null) {
            dfsPathsString(root.getLeft(), tmp + root.getVal() + "->", res);
        }
        if (root.getRight() != null) {
            dfsPathsString(root.getRight(), tmp + root.getVal() + "->", res);
        }
    }
}
