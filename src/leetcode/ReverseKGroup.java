package leetcode;

import helper.ListNode;

/**
 * Created by fan on 10/19/15.
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = dummy;
        int count = 0;
        while (head != null) {
            count++;
            if (count % k == 0) {
                start = reverseList(start, head.next);
                head = start.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode start, ListNode end) {
        ListNode prev = start;
        ListNode curr = prev.next;
        while (curr != end) {
            ListNode post = curr.next;
            curr.next = prev;
            prev = curr;
            curr = post;
        }
        ListNode first = start.next;
        start.next = prev;
        first.next = curr;
        return first;
    }
}
