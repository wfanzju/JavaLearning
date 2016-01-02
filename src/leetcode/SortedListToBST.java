package leetcode;

import helper.ListNode;
import helper.TreeNode;

/**
 * Created by fan on 11/1/15.
 */
public class SortedListToBST {
    private ListNode curr;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        curr = head;
        int size = getSize(head);
        return inorderRec(0, size - 1);
    }

    private TreeNode inorderRec(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode l = inorderRec(left, mid - 1);
        TreeNode root = new TreeNode(curr.val);
        root.setLeft(l);
        curr = curr.next;
        TreeNode r = inorderRec(mid + 1, right);
        root.setRight(r);
        return root;
    }

    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }

    public static void main(String[] args) {
        SortedListToBST test = new SortedListToBST();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        test.sortedListToBST(n1);
    }
}
