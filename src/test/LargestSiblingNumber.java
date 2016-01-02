package test;

import java.util.Arrays;

/**
 * Created by fan on 11/12/15.
 */
public class LargestSiblingNumber {
    public static int solution(int n) {
        long res = 0;
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        for (int i = chars.length - 1; i >= 0; i--) {
            res = res * 10 + (int) (chars[i] - 'a');
        }
        return res > Integer.MAX_VALUE ?
                Integer.MAX_VALUE : (int) res;
    }
}
