package leetcode;

import helper.ListNode;

/**
 * Created by fan on 8/30/15.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode post = slow.next;
        ListNode pre;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = post;
            post = post.next;
            slow.next = pre;
        }
        if (fast.next == null) {
            slow = slow.next;
        }

        while (post != null) {
            if (slow.val != post.val) {
                return false;
            }
            slow = slow.next;
            post = post.next;
        }
        return true;
    }
}
