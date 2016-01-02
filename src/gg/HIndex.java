package gg;

import java.util.Arrays;

/**
 * Created by fan on 11/8/15.
 */
public class HIndex {
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            if (citations[i] >= len - i) {
                return len - i;
            }
        }
        return 0;
    }

    public static int hIndexBS(int[] citations) {
        int len = citations.length;
        int l = 0;
        int r = citations.length - 1;
        int hIdx=0;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (citations[m] >= len - m) {
                hIdx = len - m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return hIdx;
    }

    public static int hIndexOn(int[] citations) {
        int len = citations.length;
        int[] count = new int[len + 1];
        for (int c : citations) {
            if (c > len) {
                count[len]++;
            } else {
                count[c]++;
            }
        }
        int total = 0;
        for (int i = len; i >= 0; i--) {
            total += count[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }

    public static int hIndexQuickSelect(int[] citations) {
        int len = citations.length;
        int s = 0;
        int e = len - 1;
        int hIdx = 0;

        while (s <= e) {
            int curr = partition(citations, s, e);
            if (citations[curr] >= len - curr) {
                hIdx = len - curr;
                e = curr - 1;
            } else {
                s = curr + 1;
            }
        }
        return hIdx;
    }

    private static int partition(int[] citations, int s, int e) {
        if (s == e) {
            return s;
        }
        int pivot = citations[e];
        int i = s;
        for (int j = s; j < e; j++) {
            if (citations[j] < pivot) {
                swap(citations, i, j);
                i++;
            }
        }
        swap(citations, e, i);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
