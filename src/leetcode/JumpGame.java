package leetcode;

/**
 * Created by fan on 10/24/15.
 */
public class JumpGame {
    public static int minJumpStep(int[] arr) {
        int minStep = 0;
        // current max reachable index when step is minStep
        int currMaxReach = 0;
        int nextMaxReach = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i > nextMaxReach) {
                return -1;
            } else if (i > currMaxReach) {
                minStep++;
                currMaxReach = nextMaxReach;
                if (currMaxReach >= arr.length - 1) {
                    return minStep;
                }
            }
            nextMaxReach = Math.max(nextMaxReach, i + arr[i]);
        }
        return minStep;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 0};
        System.out.println(minJumpStep(arr));
    }
}
