package gg;

/**
 * Created by fan on 11/13/15.
 */
public class NthDigit {
    public static int getNth(int n) {
        int count = 9;
        int digit = 1;
        while (n - count * digit > 0) {
            n -= count * digit;
            count *= 10;
            digit++;
        }
        int offset = n / digit;
        int start = (int) Math.pow(10, digit - 1);
        int num = start + offset;
        int order = n % digit;
        int[] arr = new int[digit];
        for (int i = digit - 1; i >= order; i--) {
            arr[i] = num % 10;
            num /= 10;
        }
        return arr[order];
    }
}
