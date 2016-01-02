package leetcode;

import helper.ListNode;

/**
 * Created by fan on 7/18/15.
 */

public class RemoveListElements {
    public ListNode recursive(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = recursive(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode iterative(ListNode head, int val) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode curr = head;
        ListNode prev = fakeHead;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }
        return fakeHead.next;
    }
}
