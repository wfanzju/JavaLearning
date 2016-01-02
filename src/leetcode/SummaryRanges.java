package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/5/15.
 */
public class SummaryRanges {
    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 1) {
            res.add(nums[0] + "");
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                i++;
            }
            if (curr != nums[i]) {
                res.add(curr + "->" + nums[i]);
            } else {
                res.add(curr + "");
            }
        }
        return res;
    }

    public List<String> findMissingRanges(int[] nums, int s, int e) {
        List<String> res = new ArrayList<>();
        int next = s;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == next) {
                next++;
            } else {
                res.add(getRange(next, nums[i] - 1));
                next = nums[i] + 1;
            }
        }
        if (next <= e) {
            res.add(getRange(next, e));
        }
        return res;
    }

    private String getRange(int s, int e) {
        return s == e ? String.valueOf(s) : String.format("%d->%d", s, e);
    }
}
