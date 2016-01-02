package gg;

import java.util.*;

/**
 * Created by fan on 11/6/15.
 */
public class WeightedRandomSelection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<>();
    private final List<Double> keyList = new ArrayList<>();
    private final Random random = new Random();
    private double total = 0;

    public void add(double weight, E value) {
        if (weight <= 0) {
            return;
        }
        total += weight;
        map.put(total, value);
        keyList.add(total);
    }

    public E next() {
        double target = random.nextDouble() * total;
        double k1 = keyList.get(findCeilingKey(target));
        double k2 = map.ceilingKey(target);
        if (k1 != k2) {
            throw new RuntimeException("Wrong algorithm");
        }
        return map.get(k2);
    }

    private int findCeilingKey(double target) {
        int l = 0;
        int r = keyList.size() - 1;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (keyList.get(m) < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        WeightedRandomSelection<Integer> test = new WeightedRandomSelection<>();
        test.add(1.1, 1991);
        test.add(0.8, 2009);
        test.add(0.6, 2015);
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        for (int i = 0; i < 100000; i++) {
            switch (test.next()) {
                case 1991:
                    c1++;
                    break;
                case 2009:
                    c2++;
                    break;
                case 2015:
                    c3++;
                    break;
            }
        }
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
