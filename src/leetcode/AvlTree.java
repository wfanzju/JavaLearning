package leetcode;

import java.util.Arrays;

/**
 * Created by fan on 10/26/15.
 */
class AvlTreeNode<T> {
    T val;
    AvlTreeNode<T> left;
    AvlTreeNode<T> right;
    int height;
    long sizeOfSubtree;

    public AvlTreeNode(T val) {
        this.val = val;
        left = null;
        right = null;
        height = 1;
        sizeOfSubtree = 1;
    }
}

public class AvlTree<T extends Comparable<? super T>> {
    private static final int ALLOWED_IMBALANCE = 1;
    private AvlTreeNode<T> root;

    public AvlTree() {
        root = null;
    }

    public void insert(T x, int[] countLarger) {
        root = insert(x, root, countLarger);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    private AvlTreeNode<T> insert(T x, AvlTreeNode<T> t, int[] countLarger) {
        if (t == null) {
            return new AvlTreeNode<>(x);
        }
        int compareRes = x.compareTo(t.val);
        if (compareRes < 0) {
            countLarger[0] += getSizeOfSubtree(t.right) + 1;
            t.left = insert(x, t.left, countLarger);
        } else if (compareRes > 0) {
            t.right = insert(x, t.right, countLarger);
        }
        t.height = 1 + Math.max(t.left == null ? 0 : t.left.height,
                t.right == null ? 0 : t.right.height);
        t.sizeOfSubtree = 1 + getSizeOfSubtree(t.left) + getSizeOfSubtree(t.right);
        return balance(t);
    }

    private long getSizeOfSubtree(AvlTreeNode node) {
        return node == null ? 0 : node.sizeOfSubtree;
    }

    private AvlTreeNode<T> remove(T x, AvlTreeNode<T> t) {
        if (t == null) {
            return null;
        }
        int compareRes = x.compareTo(t.val);
        if (compareRes < 0) {
            t.left = remove(x, t.left);
        } else if (compareRes > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.val = findMin(t.right).val;
            t.right = remove(t.val, t.right);
        } else {
            t = t.left != null ? t.left : t.right;
        }
        return balance(t);
    }

    private AvlTreeNode<T> findMin(AvlTreeNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private AvlTreeNode<T> findMax(AvlTreeNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private AvlTreeNode<T> balance(AvlTreeNode<T> t) {
        return t;
    }

    public static long countLeftLarger(long[] arr) {
        AvlTree<Long> tree = new AvlTree<>();
        int[] leftLarger = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int[] res = new int[1];
            tree.insert(arr[i], res);
            leftLarger[i] = res[0];
        }
        System.out.println(Arrays.toString(leftLarger));
        return 0;
    }

    public static void main(String[] args) {
        long[] arr = new long[]{10, 6, 15, 20, 30, 5, 7};
        countLeftLarger(arr);
    }
}
