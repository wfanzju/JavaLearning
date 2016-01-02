package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 10/8/15.
 */
public class WildCardPattern {
    public static List<String> wildCardPattern(String str) {
        List<String> res = new ArrayList<>();
        dfs(res, str);
        return res;
    }

    private static void dfs(List<String> res, String str) {
        if (!str.contains("?")) {
            res.add(str);
            return;
        }
        dfs(res, str.replaceFirst("\\?", "0"));
        dfs(res, str.replaceFirst("\\?", "1"));
    }
}
