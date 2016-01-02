package gg;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fan on 11/16/15.
 */
public class MatchStrList {
    public static boolean contains(String pattern, Iterator<String> strs) {
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < pattern.length()) {
            if (j >= sb.length()) {
                if (!strs.hasNext()) {
                    return false;
                }
                sb.append(strs.next());
            }
            if (sb.charAt(j) == pattern.charAt(i)) {
                i++;
                j++;
            } else {
                i = 0;
                j = 0;
                sb.deleteCharAt(0);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("ab","ab","bc");
        System.out.println(contains("abc",list.iterator()));
    }
}
