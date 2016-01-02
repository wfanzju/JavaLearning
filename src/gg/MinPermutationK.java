package gg;

import java.util.PriorityQueue;

/**
 * Created by fan on 11/6/15.
 */
public class MinPermutationK {
    private static class Element {
        char c;
        int idx;

        Element(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }

    public static String minPermutationK(String str, int k) {
        StringBuilder res = new StringBuilder();
        PriorityQueue<Element> cpq = new PriorityQueue<>(k,
                (x, y) -> (x.c == y.c ?
                        Integer.compare(x.idx, y.idx) :
                        Character.compare(x.c, y.c)));
        PriorityQueue<Element> ipq = new PriorityQueue<>(k, (x, y) -> Integer.compare(x.idx, y.idx));
        int i;
        for (i = 0; i < Math.min(k, str.length()); i++) {
            Element e = new Element(str.charAt(i), i);
            cpq.add(e);
            ipq.add(e);
        }
        while (!ipq.isEmpty()) {
            if (i < str.length()) {
                Element e = new Element(str.charAt(i), i);
                cpq.add(e);
                ipq.add(e);
                i++;
            }
            if (ipq.peek().idx + k == res.length()) {
                res.append(ipq.peek().c);
                cpq.remove(ipq.poll());
            } else {
                res.append(cpq.peek().c);
                ipq.remove(cpq.poll());
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(minPermutationK("bcdaea", 1));
    }
}
