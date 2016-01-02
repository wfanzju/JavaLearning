package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/16/15.
 */
public class Codec {
    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s.length()).append('/').append(s);
        }
        return res.toString();
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int start = s.indexOf('/', i);
            int size = Integer.valueOf(s.substring(i, start));
            res.add(s.substring(start + 1, start + size + 1));
            i = start + size + 1;
        }
        return res;
    }
}
