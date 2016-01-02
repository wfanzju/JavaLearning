package leetcode;

/**
 * Created by fan on 7/13/15.
 */
public class HouseRobberII {
    public int rob(int[] num){
        if(num.length == 0){
            return 0;
        }
        if(num.length == 1){
            return num[0];
        }
        return Math.max(robHelper(num, 0, num.length-1),
                robHelper(num, 1, num.length));
    }
    private int robHelper(int[] num, int start, int end){
        int robPre = 0;
        int noPre = 0;
        for(int i = start; i < end; i++){
            int tmp = noPre;
            noPre = Math.max(noPre, robPre);
            robPre = tmp + num[i];
        }
        return Math.max(robPre, noPre);
    }
}
