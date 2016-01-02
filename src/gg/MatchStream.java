package gg;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fan on 10/29/15.
 */
public class MatchStream {
    private final Map<String, Integer> wordMap;

    public MatchStream(String[] words) {
        wordMap = Arrays.asList(words).stream()
                .collect(Collectors.toMap(s -> s, this::strToInt));
    }

    private int strToInt(String word) {
        int res = 0;
        for (char c : word.toCharArray()) {
            res |= (1 << (c - 'a'));
        }
        return res;
    }

    private int unsetBit(int val, char c) {
        return val & ~(1 << (c - 'a'));
    }

    public void isMatch(char c) {
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            int newVal = unsetBit(entry.getValue(), c);
            wordMap.put(entry.getKey(), newVal);
            if (newVal == 0) {
                System.out.println(entry.getKey());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(1 << 3));
        System.out.println(Integer.toBinaryString(~(1 << 3)));
        System.out.println(Integer.toBinaryString(-(1 << 3)));
        int mask = ((1 << 3) - 1);
        System.out.println(Integer.toBinaryString(mask));
        MatchStream ms = new MatchStream(new String[]{"b", "a", "oc", "ax"});
        for (char c = 'a'; c < 'p'; c++) {
            ms.isMatch(c);
        }
    }
}
