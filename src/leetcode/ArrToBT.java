package leetcode;

import helper.TreeNode;

/**
 * Created by fan on 10/25/15.
 */
public class ArrToBT {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildRec(preorder, 0, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildRec(int[] preorder, int preStart,
                              int[] inorder, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIdx = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.getVal()) {
                inIdx = i;
            }
        }
        root.setLeft(buildRec(preorder, preStart + 1, inorder, inStart, inIdx - 1));
        root.setRight(buildRec(preorder, preStart + inIdx - inStart + 1, inorder, inIdx + 1, inEnd));
        return root;
    }
}
