package gg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 10/8/15.
 */
public class WordConcatenation {
    static boolean checkConcatenation(Set<String> dict, String words) {
        boolean[] table = new boolean[words.length() + 1];
        table[0] = true;
        for (int i = 1; i <= words.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (table[j] && dict.contains(words.substring(j, i))) {
                    table[i] = true;
                    break;
                }
            }
        }
        return table[words.length()];
    }

    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("hello");
        dict.add("world");
        System.out.print(checkConcatenation(dict, "helloworld"));
        System.out.print(checkConcatenation(dict, "hellohello"));
    }
}
