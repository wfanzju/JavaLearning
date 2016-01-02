package gg;

import java.util.*;

/**
 * Created by fan on 11/20/15.
 */
public class FisherYatesShuffle<T> {
    private static final Random random = new Random();

    public static <T> void shuffle(T[] a) {
        int n = a.length;
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            T tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
        }
    }

    public static <T> List<T> shuffleUnknown(Iterator<T> data) {
        List<T> res = new ArrayList<>();
        while (data.hasNext()) {
            int j = random.nextInt(res.size() + 1);
            if (j == res.size()) {
                res.add(data.next());
            } else {
                res.add(res.get(j));
                res.set(j, data.next());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(shuffleUnknown(Arrays.asList(3,4,2,5).iterator()));
    }
}
