package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 10/31/15.
 */
public class ParenthesisGeneration {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res,"",0,0,n);
        return res;
    }

    private void dfs(List<String> res, String s, int open, int close, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }
        if (open < n) {
            dfs(res, s + "(", open + 1, close, n);
        }
        if (close < open) {
            dfs(res, s + ")", open, close + 1, n);
        }
    }
}
