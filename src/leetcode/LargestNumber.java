package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by fan on 7/10/15.
 */
public class LargestNumber {

    public String largestNum(int[] num){
        if(Arrays.stream(num).allMatch(x -> x == 0)){
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        Arrays.stream(num)
                .mapToObj(String::valueOf)
                .sorted((x, y) -> (y + x).compareTo(x + y))
//                .sorted(new Comparator<String>() {
//                    @Override
//                    public int compare(String s1, String s2) {
//                        return (s2 + s1).compareTo(s1 + s2);
//                    }
//                })
                .forEachOrdered(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber test = new LargestNumber();
        System.out.println(test.largestNum(new int[]{1, 2, 3}));
    }
}
