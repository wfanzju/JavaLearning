package CLRS;

import java.util.Arrays;

/**
 * Created by fan on 10/28/15.
 */
public class MaxHeap<T extends Comparable<? super T>> {
    T[] maxHeap;
    int heapSize;

    public MaxHeap(T[] arr) {
        maxHeap = arr;
        heapSize = arr.length;
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public static void heapSort(String[] arr){
        MaxHeap<String> heap = new MaxHeap<>(arr);
        for(int i = arr.length-1; i>0; i--){
            String largest = arr[0];
            arr[0] = arr[i];
            arr[i] = largest;
            heap.heapSize--;
            heap.maxHeapify(0);
        }
    }

    private int parentIndx(int i) {
        return i == 0 ? 0 : (i - 1) / 2;
    }

    private int leftIndx(int i) {
        return i * 2 + 1;
    }

    private int rightIndx(int i) {
        return i * 2 + 2;
    }

    private void maxHeapify(int i) {
        int l = leftIndx(i);
        int r = rightIndx(i);
        int largest = i;
        if (l < heapSize && maxHeap[l].compareTo(maxHeap[largest]) > 0) {
            largest = l;
        }
        if (r < heapSize && maxHeap[r].compareTo(maxHeap[largest]) > 0) {
            largest = r;
        }
        if (largest != i) {
            T tmp = maxHeap[largest];
            maxHeap[largest] = maxHeap[i];
            maxHeap[i] = tmp;
            maxHeapify(largest);
        }
    }

    private void bottomUp(int i){
        int p = parentIndx(i);
        while(p!=i && maxHeap[p].compareTo(maxHeap[i])<0){
            T tmp = maxHeap[p];
            maxHeap[p] = maxHeap[i];
            maxHeap[i] = tmp;
            i = p;
            p = parentIndx(i);
        }
    }

    public static void main(String[] args){
        String[] test = new String[]{"c","bg","aaf","xxx","hello","hella"};
        heapSort(test);
        System.out.println(Arrays.toString(test));
    }
}
