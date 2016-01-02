package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fan on 10/24/15.
 */
public class Subset {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, list, nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list,
                     int[] num, int pos) {
        if (pos <= num.length) {
            res.add(new ArrayList<>(list));
        }
        while (pos < num.length) {
            list.add(num[pos]);
            dfs(res, list, num, ++pos);
            list.remove(list.size() - 1);
            while (pos < num.length && num[pos] == num[pos - 1]) {
                pos++;
            }
        }
    }
}
