package gg;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by fan on 10/30/15.
 */
public class RearrangeString {
    public static String rearrange(String s) {
        StringBuilder res = new StringBuilder();
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.putIfAbsent(c, 0);
            freq.put(c, freq.get(c) + 1);
        }
        PriorityQueue<Character> q = new PriorityQueue<>((a, b) -> -freq.get(a).compareTo(freq.get(b)));
        freq.keySet().forEach(x -> q.add(x));
        while (!q.isEmpty()) {
            char c = q.poll();
            res.append(c);
            freq.put(c, freq.get(c) - 1);
            if (q.isEmpty()) {
                if (freq.get(c) != 0) {
                    System.out.println("Input cannot be rearranged!");
                }
                return res.toString();
            }
            char d = q.poll();
            res.append(d);
            freq.put(d, freq.get(d) - 1);
            if (freq.get(c) > 0) {
                q.add(c);
            }
            if (freq.get(d) > 0) {
                q.add(d);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(rearrange("AAABBBBBBC"));
    }
}
