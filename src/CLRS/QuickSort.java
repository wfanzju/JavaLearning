package CLRS;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by fan on 10/28/15.
 */
public class QuickSort {
    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition(arr, start, end);
        quickSort(arr, start, mid - 1);
        quickSort(arr, mid + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = tmp;
        return i + 1;
    }

    private static void quickSort2(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = randPartition(arr, start, end);
        quickSort2(arr, start, mid);
        quickSort2(arr, mid + 1, end);
    }

    private static final Random rand = new Random();

    private static int randPartition(int[] A, int s, int e) {
        int randIdx = rand.nextInt(e - s + 1) + s;
        int pivot = A[randIdx];
        int i = s - 1;
        int j = e + 1;
        while (true) {
            do {
                j--;
            } while (A[j] > pivot);
            do {
                i++;
            } while (A[i] < pivot);
            if (i < j) {
                int tmp = A[j];
                A[j] = A[i];
                A[i] = tmp;
            } else {
                // j+1->e are >pivot, s->i-1 are <pivot
                return j;
            }
        }
    }

    public static void quickSort(int[] A) {
        quickSort2(A, 0, A.length - 1);
    }

    private static int randomSelect(int[] A, int s, int e, int k) {
        if (s == k) {
            return A[s];
        }
        int cut = randPartition(A, s, e);
        int cutK = cut - s + 1;
        if (cutK == k) {
            return A[cut];
        } else if (cutK < k) {
            return randomSelect(A, cut + 1, e, k - cutK);
        } else {
            return randomSelect(A, s, cut - 1, k);
        }
    }

    public static int findKth(int[] A, int k) {
        return randomSelect(A, 0, A.length - 1, k - 1);
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 3, 6, 5, 2, 7, 8, 4};
        System.out.println(findKth(test, 5));
        System.out.println(Arrays.toString(test));
        quickSort(test);
        System.out.println(Arrays.toString(test));
    }
}
