package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/18/15.
 */
public class AndroidPwd {
    private static int[][] dir = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};

    private boolean inside(int i, int j, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    public List<String> allPossiblePwd(int[][] grid) {
        List<String> res = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                boolean[][] visited = new boolean[m][n];
                StringBuilder sb = new StringBuilder();
                dfs(sb, i, j, grid, visited, res);
            }
        }
        return res;
    }

    private void dfs(StringBuilder sb, int i, int j, int[][] grid,
                     boolean[][] visited, List<String> res) {
        if (sb.length() > 4) {
            return;
        }
        sb.append(grid[i][j]);
        visited[i][j] = true;
        if (sb.length() >= 3) {
            res.add(sb.toString());
        }
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (!inside(x, y, grid)) {
                continue;
            }
            if (!visited[x][y]) {
                dfs(sb, x, y, grid, visited, res);
            } else {
                while (inside(x, y, grid) && visited[x][y]) {
                    x += d[0];
                    y += d[1];
                }
                if (!inside(x, y, grid)) {
                    continue;
                }
                dfs(sb, x, y, grid, visited, res);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        AndroidPwd test = new AndroidPwd();
        int[][] grid = new int[][]{{0, 1}, {2, 3}};
        System.out.println(test.allPossiblePwd(grid));
    }
}
