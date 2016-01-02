package leetcode;

/**
 * Created by fan on 10/31/15.
 */
public class DivideIntegers {
    // a = b(2^x + 2^y+...)
    public static int divide(int a, int b) {
        if (b == 0) {
            return Integer.MAX_VALUE;
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        boolean flag = (a < 0) ^ (b < 0);
        long x = Math.abs((long) a);
        long y = Math.abs((long) b);
        int res = 0;
        while (x >= y) {
            int shift = 1;
            while (x >= (y << shift)) {
                shift++;
            }
            res |= 1 << (shift - 1);
            x -= (y << (shift - 1));
        }
        return flag ? -res : res;
    }

    public static void main(String[] args) {
        System.out.println(divide(-1010369383, -2147483648));
    }
}
