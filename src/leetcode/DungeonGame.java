package leetcode;

import java.util.Arrays;

/**
 * Created by fan on 7/18/15.
 */
public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        final int m = dungeon.length;
        final int n = dungeon[0].length;
        int[][] minHP = new int[m + 1][n + 1];
        for (int[] row : minHP) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        minHP[m][n - 1] = 1;
        minHP[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                minHP[i][j] = Math.max(1,
                        Math.min(minHP[i + 1][j], minHP[i][j + 1])
                                - dungeon[i][j]);
            }
        }
        return minHP[0][0];
    }

    public int arrayTableFilling(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        final int m = dungeon.length;
        final int n = dungeon[0].length;
        int[] minHP = new int[n + 1];
        Arrays.fill(minHP, Integer.MAX_VALUE);
        minHP[n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                minHP[j] = Math.max(1,
                        Math.min(minHP[j], minHP[j + 1])
                                - dungeon[i][j]);
            }
        }
        return minHP[0];
    }
}
