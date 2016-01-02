package gg;

import helper.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by fan on 11/1/15.
 */
public class LargestMFromNLists {
    public static List<Integer> selectMLargest(List<ListNode> lists, int m) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<ListNode> q =
                new PriorityQueue<>(m, (a, b) -> Integer.compare(a.val, b.val));
        for (int i = 0; i < lists.size(); i++) {
            ListNode currHead = lists.get(i);
            for (int j = 0; j < m; j++) {
                if (currHead == null
                        || (q.size() == m && q.peek().val >= currHead.val)) {
                    break;
                }
                q.add(currHead);
                if (q.size() > m) {
                    q.poll();
                }
                currHead = currHead.next;
            }
        }
        while (!q.isEmpty()) {
            res.add(q.poll().val);
        }
        return res;
    }
}
