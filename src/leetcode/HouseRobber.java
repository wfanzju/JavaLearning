package leetcode;

/**
 * Created by fan on 7/12/15.
 */
public class HouseRobber {

    public int robDP(int[] num){
        if(num == null || num.length == 0){
            return 0;
        }
        int[][] robAmount = new int[num.length][2];
        robAmount[0][0] = 0;
        robAmount[0][1] = num[0];
        for(int i=1; i<num.length; i++){
            robAmount[i][0] = Math.max(robAmount[i-1][0], robAmount[i-1][1]);
            robAmount[i][1] = robAmount[i-1][0] + num[i];
        }
        return Math.max(robAmount[num.length-1][0], robAmount[num.length-1][1]);
    }

    public int robO1(int[] num){
        int robPre = 0;
        int noPre = 0;
        for(int n : num){
            int tmp = noPre;
            noPre = Math.max(noPre, robPre);
            robPre = tmp + n;
        }
        return Math.max(robPre, noPre);
    }
}
