package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fan on 11/21/15.
 */
public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; ) {
            res.add(s.substring(0, i) + "--" + s.substring(i + 2));
        }
        return res;
    }

    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        Map<String, Boolean> winMap = new HashMap<>();
        return canWin(s, winMap);
    }

    public boolean canWin(String s, Map<String, Boolean> winMap) {
        if (winMap.containsKey(s)) {
            return winMap.get(s);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(opponent, winMap)) {
                    winMap.put(s, true);
                    return true;
                }
            }
        }
        winMap.put(s, false);
        return false;
    }
}
