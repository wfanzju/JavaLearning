package gg;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;


/**
 * Created by fan on 11/19/15.
 */
public class RecoverArr<T extends Comparable<? super T>> {

    class TreeNode {
        T val;
        int rightLargerCount;
        TreeNode left;
        TreeNode right;

        TreeNode(T val, int countLarger) {
            this.val = val;
            this.rightLargerCount = countLarger;
        }
    }

    private TreeNode constructBST(T[] arr, int left, int right) {
        if (left > right) {
            return null;
        } else {
            int mid = left + ((right - left) >> 1);
            TreeNode root = new TreeNode(arr[mid], right - mid + 1);
            root.left = constructBST(arr, left, mid - 1);
            root.right = constructBST(arr, mid + 1, right);
            return root;
        }
    }

    private T getNthLargest(TreeNode root, int order) {
        if (root == null) {
            return null;
        }
        if (root.rightLargerCount == order) {
            return root.val;
        } else if (root.rightLargerCount > order) {
            return getNthLargest(root.right, order);
        } else {
            return getNthLargest(root.left, order - root.rightLargerCount);
        }
    }

    private TreeNode findMin(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }

    private TreeNode rmNthLargest(TreeNode root, int order) {
        if (root.rightLargerCount > order) {
            root.rightLargerCount--;
            root.right = rmNthLargest(root.right, order);
        } else if (root.rightLargerCount < order) {
            root.left = rmNthLargest(root.left, order - root.rightLargerCount);
        } else if (root.right == null) {
            return root.left;
        } else {
            TreeNode nextLarger = findMin(root.right);
            root.val = nextLarger.val;
            root.rightLargerCount--;
            root.right = rmNthLargest(root.right, order - 1);
        }
        return root;
    }

    public T[] recoverArrNlogn(int[] countArr, T[] arr) {
        T[] res = (T[]) Array.newInstance(arr[0].getClass(), arr.length);
        Arrays.sort(arr);
        TreeNode root = constructBST(arr, 0, arr.length - 1);
        for (int i = arr.length - 1; i >= 0; i--) {
            int order = countArr[i] + 1;
            res[i] = getNthLargest(root, order);
            rmNthLargest(root, order);
        }
        return res;
    }

    public T[] recoverArr(int[] countArr, T[] arr) {
        T[] res = (T[]) Array.newInstance(arr[0].getClass(), arr.length);
        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = arr.length - 1; i >= 0; i--) {
            int order = countArr[i] + 1;
            res[i] = getNthLargest(arr, order);
            rmNthEle(arr, order);
        }
        return res;
    }

    private T getNthLargest(T[] nums, int order) {
        return nums[order - 1];
    }

    private void rmNthEle(T[] nums, int order) {
        for (int i = order - 1; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
    }

    public static void main(String[] args) {
        RecoverArr<Double> test = new RecoverArr<>();
        Double[] height = new Double[]{7.8, 9.3, 3.5};
        int[] countArr = new int[]{0, 1, 1};
        //Double[] res = test.recoverArr(countArr, height);
        //System.out.println(Arrays.toString(res));

        Double[] res = test.recoverArrNlogn(countArr, height);
        System.out.println(Arrays.toString(res));
    }
}
