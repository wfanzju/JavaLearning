package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 10/22/15.
 */
public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder res = new StringBuilder();
        String sign = ((numerator < 0) ^ (denominator < 0)) && numerator != 0
                ? "-" : "";
        res.append(sign);
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        res.append(num / den);
        long remainder = num % den;
        if (remainder == 0) {
            return res.toString();
        }
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (!map.containsKey(remainder) && remainder != 0) {
            map.put(remainder, res.length());
            res.append(10 * remainder / den);
            remainder = 10 * remainder % den;
        }
        if (remainder != 0) {
            int index = map.get(remainder);
            res.insert(index, "(");
            res.append(")");
        }
        return res.toString();
    }
}
