package gg;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/13/15.
 */
public class EvenlyCutTree {
    public static TreeNode evenCutTree(TreeNode root) {
        List<Integer> sumArr = new ArrayList<>();
        List<TreeNode> postOrder = new ArrayList<>();
        computeSum(root, sumArr, postOrder);
        int totalSum = sumArr.get(sumArr.size() - 1);
        int minDiff = totalSum;
        TreeNode cutNode = root;
        for (int i = 0; i < sumArr.size(); i++) {
            int diff = Math.abs(sumArr.get(i) * 2 - totalSum);
            if (diff < minDiff) {
                cutNode = postOrder.get(i);
                minDiff = diff;
            }
        }
        return cutNode;
    }

    private static int computeSum(TreeNode root, List<Integer> sumArr, List<TreeNode> postOrder) {
        if (root == null) {
            return 0;
        }
        int value = computeSum(root.getLeft(), sumArr, postOrder)
                + computeSum(root.getRight(), sumArr, postOrder)
                + root.getVal();
        sumArr.add(value);
        postOrder.add(root);
        return value;
    }

    private static TreeNode findParent(TreeNode root, int target, TreeNode parent) {
        if (root == null) {
            return null;
        }
        if (root.getVal() == target) {
            return parent;
        }
        parent = findParent(root.getLeft(), target, root);
        if (parent == null) {
            parent = findParent(root.getRight(), target, root);
        }
        return parent;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(2));
        root.setRight(new TreeNode(3));
        TreeNode n = evenCutTree(root);
        TreeNode p = findParent(root, n.getVal(), null);
        System.out.println(p.getVal());
    }

}
