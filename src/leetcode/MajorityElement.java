package leetcode;

import java.util.Arrays;

/**
 * Created by fan on 7/8/15.
 */
public class MajorityElement {

    // Hash Table can also solve this in O(n) time and O(n) space

    // Divide and conquer takes O(n log n) time.
    // Compute to get two majority from two parts, check occurrences for the two candidates.
    // T(n) = T(n/2) + 2n = O(n log n)

    public int sorting(int[] num){
        Arrays.sort(num);
        return num[num.length/2];
    }

    public int mooreVoting(int[] num){
        int majority = 0;
        int vote = 0;
        for(int i=0; i<num.length; i++){
            if(vote==0){
                majority = num[i];
                vote++;
            }else if(majority==num[i]){
                vote++;
            }else{
                vote--;
            }
        }
        return majority;
    }

    public int bitManipulating(int[] num){
        int majority = 0;
        for(int i=0; i<32; i++){
            int ones = 0;
            for(int n : num){
                ones += (n>>i) & 1;
            }
            majority |= (ones > num.length/2)? 1 << i : 0;
        }
        return majority;
    }
}
