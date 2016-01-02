package gg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 10/31/15.
 */
public class LongestSubstrNoRepeat {
    public int lenLongestSubstr(String s) {
        int start = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        int i = 0;
        while (i < s.length()) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i++));
                maxLen = Math.max(maxLen, set.size());
            } else {
                set.remove(s.charAt(start++));
            }
        }
        return maxLen;
    }
}
