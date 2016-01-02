package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/8/15.
 */
public class WordAbbreviation {
    public static List<String> getAbbrevation(String word) {
        List<String> res = new ArrayList<>();
        dfs(new StringBuilder(), 0, word, res);
        return res;
    }

    private static void dfs(StringBuilder part, int curr, String word, List<String> res) {
        if (curr >= word.length()) {
            res.add(part.toString());
            return;
        }
        part.append(word.charAt(curr));
        dfs(part, curr + 1, word, res);
        part.deleteCharAt(part.length() - 1);
        if (part.length() == 0 || !isDigit(part.charAt(part.length() - 1))) {
            for (int i = curr; i < word.length(); i++) {
                part.append(i - curr + 1);
                dfs(part, i + 1, word, res);
                part.deleteCharAt(part.length() - 1);
            }
        }
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        System.out.println(getAbbrevation("abcd"));
    }
}
