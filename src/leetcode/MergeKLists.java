package leetcode;

import helper.ListNode;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by fan on 10/31/15.
 */
public class MergeKLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>(lists.size(),
                (a, b) -> Integer.compare(a.val, b.val));
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (ListNode n : lists) {
            if (n != null) {
                q.add(n);
            }
        }
        while (!q.isEmpty()) {
            curr.next = q.poll();
            curr = curr.next;
            if (curr.next != null) {
                q.add(curr.next);
            }
        }
        return dummy.next;
    }
}
