package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/21/15.
 */
public class BestMeetingPoint {

    public int minTotalDist(int[][] grid) {
        int res = minDist(grid);
        transpose(grid);
        return res + minDist(grid);
    }

    private static void transpose(int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    private int minDist(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    pos.add(i);
                }
            }
        }
        int total = 0;
        int left = 0;
        int right = pos.size() - 1;
        while (left < right) {
            total += pos.get(right--) - pos.get(left++);
        }
        return total;
    }
}
