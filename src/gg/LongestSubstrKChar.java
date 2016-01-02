package gg;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by fan on 11/1/15.
 */
public class LongestSubstrKChar {
    public static String longestSubstrKUniqChars(String s, int k) {
        Map<Character, Integer> counter = new HashMap<>();
        int start = 0;
        int maxLen = 0;
        String maxSubstr = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counter.putIfAbsent(c, 0);
            counter.put(c, counter.get(c) + 1);
            while (counter.size() > k) {
                char first = s.charAt(start);
                counter.put(first, counter.get(first) - 1);
                if (counter.get(first) == 0) {
                    counter.remove(first);
                }
                start++;
            }
            int range = i - start + 1;
            if (range > maxLen) {
                maxLen = range;
                maxSubstr = s.substring(start, i + 1);
            }
        }
        return maxSubstr;
    }

    public static void main(String[] args) {
        String s = "abaccdcefggff";
        System.out.println(longestSubstrKUniqChars(s, 3));
    }
}