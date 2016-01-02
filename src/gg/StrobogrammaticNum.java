package gg;

import java.util.*;

/**
 * Created by fan on 11/17/15.
 */
public class StrobogrammaticNum {
    private static final Map<Character, Character> map = new HashMap<>();

    static {
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
    }

    public List<String> findStroNum(int n) {
        List<String> res = ((n & 1) == 0) ? Arrays.asList("") : Arrays.asList("0", "1", "8");
        for (int i = 1 + (n % 2); i < n; i += 2) {
            List<String> curr = new ArrayList<>();
            for (String s : res) {
                for (char c : map.keySet()) {
                    if (i == n - 1 && c == '0') {
                        continue;
                    }
                    curr.add(c + s + map.get(c));
                }
            }
            res = curr;
        }
        return res;
    }

    public static void main(String[] args) {
        StrobogrammaticNum test = new StrobogrammaticNum();
        System.out.println(test.findStroNum(3));
    }
}
