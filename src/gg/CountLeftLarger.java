package gg;

import java.util.Arrays;

/**
 * Created by fan on 11/3/15.
 */
public class CountLeftLarger {
    static class BST {
        private class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int val) {
                this.val = val;
            }
        }

        private TreeNode root;
        private int leftLargerCount;

        public void insert(int val) {
            leftLargerCount = 0;
            root = insert(val, root);
        }

        private TreeNode insert(int val, TreeNode node) {
            if (node == null) {
                return new TreeNode(val);
            }
            int compRes = Integer.compare(val, node.val);
            if (compRes < 0) {
                leftLargerCount += getTreeSize(node.right) + 1;
                node.left = insert(val, node.left);
            } else if (compRes >= 0) {
                node.right = insert(val, node.right);
            }
            return node;
        }

        private int getTreeSize(TreeNode node) {
            return node == null ?
                    0 :
                    1 + getTreeSize(node.left) + getTreeSize(node.right);
        }

    }

    public static int[] countLeftLarger(int[] arr) {
        int[] res = new int[arr.length];
        BST tree = new BST();
        for (int i = 0; i < arr.length; i++) {
            tree.insert(arr[i]);
            res[i] = tree.leftLargerCount;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 6, 15, 20, 30, 5, 7};
        System.out.println(Arrays.toString(countLeftLarger(arr)));
    }
}
