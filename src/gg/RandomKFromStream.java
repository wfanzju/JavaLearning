package gg;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by fan on 11/15/15.
 */
public class RandomKFromStream<T> {
    private final Random random = new Random();

    public T[] selectKItems(Iterator<T> str, int k) {
        int i = 0;
        T[] reservoir = (T[]) new Object[k];
        while (str.hasNext()) {
            T t = str.next();
            if (i < k) {
                reservoir[i] = t;
            } else {
                int randPos = random.nextInt(i + 1);
                if (randPos < k) {
                    reservoir[randPos] = t;
                }
            }
            i++;
        }
        return reservoir;
    }
}
