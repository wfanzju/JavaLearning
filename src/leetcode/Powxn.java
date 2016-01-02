package leetcode;

/**
 * Created by fan on 11/17/15.
 */
public class Powxn {
    public double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0 && n != Integer.MIN_VALUE) {
            return pow(1 / x, -n);
        }
        double p = pow(x, n / 2);
        return (n & 1) == 1 ? p * p * x : p * p;
    }

    public double powIter(double x, int n) {
        if (n < 0 && n != Integer.MIN_VALUE) {
            return powIter(1 / x, -n);
        }
        double res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }
}
