package leetcode;


import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by fan on 10/18/15.
 */
public class BTPostorderTraversal {

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
        recHelper(root.getRight(), res);
        res.add(root.getVal());
    }

    public List<Integer> iterative(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode tmp = stack1.pop();
            stack2.push(tmp);
            if (tmp.getLeft() != null) {
                stack1.push(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                stack1.push(tmp.getRight());
            }
        }
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().getVal());
        }
        return res;
    }

}
