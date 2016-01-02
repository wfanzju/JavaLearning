package leetcode;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by fan on 10/21/15.
 */
public class BTPreorderTraversal {
    public List<Integer> recursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recHelper(root, res);
        return res;
    }

    private void recHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.getVal());
        recHelper(root.getLeft(), res);
        recHelper(root.getRight(), res);
    }

    public List<Integer> iterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.getVal());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
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
                if(prev.getRight()==null){
                    res.add(curr.getVal());
                    prev.setRight(curr);
                    curr = curr.getLeft();
                }else{
                    prev.setRight(null);
                    curr = curr.getRight();
                }
            }
        }
        return res;
    }
}
