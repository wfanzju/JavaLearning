package leetcode;

import helper.TreeNode;

import java.util.Stack;

/**
 * Created by fan on 11/1/15.
 */
public class SortedArrayToBST {
    public TreeNode sortedArrToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode root = divideRec(num, 0, num.length - 1);
        return root;
    }

    private TreeNode divideRec(int[] num, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode node = new TreeNode(num[mid]);
        node.setLeft(divideRec(num, left, mid - 1));
        node.setRight(divideRec(num, right, mid + 1));
        return node;
    }

    public TreeNode sortedArrToBSTIter(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }
        TreeNode root = new TreeNode(0);
        Stack<TreeNode> nodeStack = new Stack<TreeNode>() {{
            push(root);
        }};
        Stack<Integer> leftIdxStack = new Stack<Integer>(){{
            push(0);
        }};
        Stack<Integer> rightIdxStack = new Stack<Integer>(){{
            push(len - 1);
        }};
        while (!nodeStack.isEmpty()) {
            TreeNode curr = nodeStack.pop();
            int left = leftIdxStack.pop();
            int right = rightIdxStack.pop();
            int mid = left + ((right - left) >> 1);
            curr.val = nums[mid];
            if (left <= mid - 1) {
                curr.left = new TreeNode(0);
                nodeStack.push(curr.left);
                leftIdxStack.push(left);
                rightIdxStack.push(mid - 1);
            }
            if (mid + 1 <= right) {
                curr.right = new TreeNode(0);
                nodeStack.push(curr.right);
                leftIdxStack.push(mid + 1);
                rightIdxStack.push(right);
            }
        }
        return root;
    }
}
