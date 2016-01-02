package gg;

/**
 * Created by fan on 10/17/15.
 */
public class BitwiseAdd {
    static int add(int x, int y) {
        //until carry==0
        while (y != 0) {
            //common bits
            int carry = x & y;
            // Exclusive OR
            x = x ^ y;
            // carry bits
            y = carry << 1;
        }
        return x;
    }

    static int addRec(int x, int y) {
        return y == 0 ?
                x : addRec(x ^ y, (x & y) << 1);
    }

    public static void main(String[] args) {
        System.out.println(add(3, 4));
    }
}
