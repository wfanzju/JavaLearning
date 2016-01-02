package gg;

import helper.TreeNode;

import java.util.Stack;

/**
 * Created by fan on 9/4/15.
 */
public class LCAofBT {
    public TreeNode lcaRec(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        // Use post-order traversal to loop through the tree.
        // Find the left and right child contains p & q and return current node as result.
        TreeNode left = lcaRec(root.getLeft(), p, q);
        TreeNode right = lcaRec(root.getRight(), p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public TreeNode lcaIter(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> pPath = new Stack<>();
        Stack<TreeNode> qPath = new Stack<>();
        TreeNode target = null;
        if (findPath(root, p, pPath) && findPath(root, q, qPath)) {
            while (!pPath.isEmpty()) {
                TreeNode pNode = pPath.pop();
                if (qPath.contains(pNode)) {
                    target = pNode;
                }
            }
        }
        return target;
    }

    private boolean findPath(TreeNode root, TreeNode node, Stack<TreeNode> path) {
        if (root == null) {
            return false;
        } else if (root == node
                || findPath(root.getLeft(), node, path)
                || findPath(root.getRight(), node, path)) {
            path.push(root);
            return true;
        }
        return false;
    }
}
