package leetcode;

import helper.ListNode;

/**
 * Created by fan on 7/20/15.
 */
public class ReverseLinkedList {

    public ListNode iterative(ListNode head){
        ListNode pre = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode recursive(ListNode head){
        if(head == null || head.next==null){
            return head;
        }
        ListNode newHead = recursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
