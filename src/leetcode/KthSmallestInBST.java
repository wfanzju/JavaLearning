package leetcode;

import helper.TreeNode;

import java.util.Stack;

/**
 * Created by fan on 8/29/15.
 */
public class KthSmallestInBST {
    private int count = 0;
    private TreeNode res = null;

    public TreeNode kthSmallest(TreeNode root, int k) {
        count = k;
        inOrderTraversal(root);
        return res;
    }

    private void inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.getLeft());
        if (--count == 0) {
            res = node;
            return;
        }
        inOrderTraversal(node.getRight());
    }

    public TreeNode kthSmallestIter(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null){
            stack.push(root);
            root = root.getLeft();
        }
        while(k!=0){
            TreeNode node = stack.pop();
            if(--k==0){
                return node;
            }
            TreeNode right = node.getRight();
            while (right!=null){
                stack.push(right);
                right = right.getLeft();
            }
        }
        return null;
    }
}
