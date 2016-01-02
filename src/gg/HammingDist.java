package gg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 11/11/15.
 */
public class HammingDist {
    public static int[] swapToReduceHammingDist(String s, String t) {
        int[] res = new int[2];
        Map<String, Integer> pairs = new HashMap<>();
        Map<Character, Integer> charPos = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) {
                continue;
            }
            String st = "" + s.charAt(i) + t.charAt(i);
            String ts = "" + t.charAt(i) + s.charAt(i);
            if (pairs.containsKey(ts)) {
                res[0] = pairs.get(ts);
                res[1] = i;
                return res;
            } else {
                pairs.put(st, i);
                charPos.put(t.charAt(i), i);
            }
        }
        for (Map.Entry<String, Integer> entry : pairs.entrySet()) {
            char c = entry.getKey().charAt(0);
            if (charPos.containsKey(c)) {
                res[0] = entry.getValue();
                res[1] = charPos.get(c);
                return res;
            }
        }
        res[0] = -1;
        res[1] = -1;
        return res;
    }
}
