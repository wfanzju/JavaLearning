package leetcode;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by fan on 10/21/15.
 */
public class BTInorderTraversal {
    public List<Integer> recursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recHelper(root, res);
        return res;
    }

    private void recHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        recHelper(root.getLeft(), res);
        res.add(root.getVal());
        recHelper(root.getRight(), res);
    }

    public List<Integer> iterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                TreeNode tmp = stack.pop();
                res.add(tmp.getVal());
                curr = tmp.getRight();
            }
        }
        return res;
    }

    public List<Integer> morris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null) {
            if (curr.getLeft() == null) {
                res.add(curr.getVal());
                curr = curr.getRight();
            } else {
                prev = curr.getLeft();
                while (prev.getRight() != null && prev.getRight() != curr) {
                    prev = prev.getRight();
                }
                if (prev.getRight() == null) {
                    prev.setRight(curr);
                    curr = curr.getLeft();
                } else {
                    res.add(curr.getVal());
                    prev.setRight(null);
                    curr = curr.getRight();
                }
            }
        }
        return res;
    }
}
