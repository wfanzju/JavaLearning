package test;

/**
 * Created by fan on 11/12/15.
 */
public class RectangleArea {
    public static int computeArea(int[] a, int[] b, int[] c, int[] d) {
        long areaOfRecAB = (b[0] - a[0]) * (b[1] - a[1]);
        long areaOfRecCD = (d[0] - c[0]) * (d[1] - c[1]);
        int left = Math.max(a[0], c[0]);
        int right = Math.min(b[0], d[0]);
        int bottom = Math.max(a[1], c[1]);
        int top = Math.min(b[1], d[1]);

        long overlap = 0;
        if (right > left && top > bottom) {
            overlap = (right - left) * (top - bottom);
        }
        long res = areaOfRecAB + areaOfRecCD - overlap;
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
    }
}
