package gg;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by fan on 10/23/15.
 */
public class CountIterator implements Iterator<Integer> {

    private int currValue;
    private int valueCount;
    private int currIndex;
    private int[] arr;

    public CountIterator(int[] arr) {
        if (arr.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid input array!");
        }
        this.arr = arr;
        currIndex = 0;
        valueCount = 0;
    }

    @Override
    public boolean hasNext() {
        if (valueCount > 0) {
            return true;
        }
        while (currIndex < arr.length && valueCount <= 0) {
            currValue = arr[currIndex++];
            valueCount = arr[currIndex++];
        }
        return valueCount > 0;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            valueCount--;
            return currValue;
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 0, 4, 0, 2, 1};
        CountIterator testIter = new CountIterator(arr);
        while (testIter.hasNext()) {
            System.out.println(testIter.next());
        }
    }
}
