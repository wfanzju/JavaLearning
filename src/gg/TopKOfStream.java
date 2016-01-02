package gg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fan on 11/19/15.
 */
public class TopKOfStream {
    public int[] topKOfStream(Iterator<Integer> data, int k) {
        int[] window = new int[2 * k];
        boolean eof = false;
        int size = readK(data, window, k, 2 * k - 1);
        if (size < k) {
            eof = true;
        }
        while (!eof) {
            size = readK(data, window, k - 1, 0);
            if (size < k) {
                eof = true;
            }
            quickSelect(window, k - size, 2 * k - 1, size);
        }
        int[] res = new int[k];
        for (int i = k; i < 2 * k; i++) {
            res[i - k] = window[i];
        }
        return res;
    }

    private int readK(Iterator<Integer> data, int[] window, int s, int e) {
        int inc = (s < e) ? 1 : -1;
        int demand = Math.abs(s - e) + 1;
        int read = 0;
        while (read < demand && data.hasNext()) {
            window[s] = data.next();
            s += inc;
            read++;
        }
        return read;
    }

    public int findKth(int[] A, int k) {
        return quickSelect(A, 0, A.length - 1, k - 1);
    }

    /**
     * QuickSelect chooses one pivot and partition the data. Then instead of recursing into
     * both sides, as in quicksort, quickselect only recurses into one side.
     * This reduces the average time complexity from O(nlgn) to O(n)
     *
     * @return
     */
    private int quickSelect(int[] A, int s, int e, int k) {
        if (s == k) {
            return A[s];
        }
        int cut = partition(A, s, e);
        int cutK = cut - s + 1;
        if (cutK == k) {
            return A[cut];
        } else if (cutK < k) {
            return quickSelect(A, cut + 1, e, k - cutK);
        } else {
            return quickSelect(A, s, cut - 1, k);
        }
    }

    private int partition(int[] A, int s, int e) {
        if (s == e) {
            return s;
        }
        int pivot = A[e];
        int i = s;
        for (int j = s; j < e; j++) {
            if (A[j] < pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, e, i);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        TopKOfStream test = new TopKOfStream();
        List<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(10);
        data.add(4);
        data.add(2);
        data.add(8);
        data.add(1);
        data.add(7);
        int[] topk = test.topKOfStream(data.iterator(), 2);
        System.out.println(Arrays.toString(topk));
    }
}
