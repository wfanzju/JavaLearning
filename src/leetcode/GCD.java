package leetcode;

/**
 * Created by fan on 9/21/15.
 */
public class GCD {
    public static int gcd(int larger, int smaller) {
        while (smaller != 0) {
            int tmp = smaller;
            smaller = larger % smaller;
            larger = tmp;
        }
        return larger;
    }

    public static int gcdRec(int a, int b) {
        return b == 0 ? a : gcdRec(b, a % b);
    }

    public static void main(String[] args) {
        int a = 24;
        int b = 40;
        System.out.print(gcd(Math.max(a, b), Math.min(a, b)));
    }
}
