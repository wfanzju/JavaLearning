package leetcode;

import java.util.*;

/**
 * Created by fan on 11/16/15.
 */
public class WordBreak {
    Map<String, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> res = new ArrayList<>();
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (int i = 1; i <= s.length(); i++) {
            String part = s.substring(0, i);
            if (!dict.contains(part)) {
                continue;
            }
            if (i == s.length()) {
                res.add(part);
            }else {
                String remain = s.substring(i);
                List<String> remainWords = wordBreak(remain, dict);
                for (String item : remainWords) {
                    res.add(part + " " + item);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
