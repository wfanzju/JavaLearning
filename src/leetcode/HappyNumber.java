package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fan on 7/14/15.
 */
public class HappyNumber {

    public boolean hashSet(int n){
        Set<Integer> seen = new HashSet<>();
        while(!seen.contains(n)){
            seen.add(n);
            int sum = 0;
            while(n > 0){
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }

    public boolean floydCycleDetection(int n){
        int slow = n;
        int fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while(slow != fast);
        return slow == 1;
    }

    private int digitSquareSum(int n){
        int sum = 0;
        while(n > 0){
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }
}
