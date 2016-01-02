package leetcode;

import helper.ListNode;

/**
 * Created by fan on 10/20/15.
 */
public class Reverse2Group {
    public static ListNode reverse2Group(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while(head!=null && head.next!=null){
            ListNode post = head.next;
            head.next = post.next;
            post.next = head;
            pre.next = post;
            pre = head;
            head = head.next;
        }
        return dummy.next;
    }
    public static void main(String[] args){
        ListNode post = null;
        for(int i=6; i>=0; i--){
            ListNode node = new ListNode(i);
            node.next = post;
            post = node;
        }
        ListNode tmp = post;
        while(tmp!=null){
            System.out.print(tmp.val);
            tmp = tmp.next;
        }
        System.out.println();
        post = reverse2Group(post);
        tmp = post;
        while(tmp!=null){
            System.out.print(tmp.val);
            tmp = tmp.next;
        }
    }
}
