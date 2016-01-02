package gg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by fan on 7/11/15.
 */
public class NumberOfIslands {

    public int floodFillDFS(char[][] grid) {
        int islands = 0;
        if (grid == null || grid.length == 0) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsFill(grid, i, j);
                    ++islands;
                }
            }
        }
        return islands;
    }

    private void dfsFill(char[][] grid, int i, int j) {
        if (!inside(i, j, grid) || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        for (int[] d : dir) {
            dfsFill(grid, i + d[0], j + d[1]);
        }
    }

    public int floodFillBFS(char[][] grid) {
        int islands = 0;
        if (grid == null || grid.length == 0) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfsFill(grid, i, j);
                    ++islands;
                }
            }
        }
        return islands;
    }

    private void bfsFill(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        int col = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * col + j);
        while (!queue.isEmpty()) {
            int code = queue.poll();
            int x = code / col;
            int y = code % col;
            for (int[] d : dir) {
                int p = x + d[0];
                int q = y + d[1];
                if (inside(p, q, grid) && grid[p][q] == '1') {
                    queue.offer(p * col + q);
                    grid[p][q] = '0';
                }
            }
        }
    }

    private boolean inside(int p, int q, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return p >= 0 && p < m && q >= 0 && q < n;
    }

    private class UnionFindIsland {
        private int[] sz;
        private int[] id;
        private int M, N, count;

        public UnionFindIsland(int m, int n) {
            M = m;
            N = n;
            sz = new int[M * N];
            id = new int[M * N];
            for (int i = 0; i < M * N; i++) {
                id[i] = i;
                sz[i] = 1;
                count++;
            }
        }

        private int find(int i) {
            while (id[i] != i) {
                i = id[i];
            }
            return i;
        }

        private void union(int x, int y) {
            int xroot = find(x);
            int yroot = find(y);
            if (xroot == yroot) {
                return;
            }
            if (sz[xroot] < sz[yroot]) {
                sz[yroot] += sz[xroot];
                id[xroot] = yroot;
            } else {
                sz[xroot] += sz[yroot];
                id[yroot] = xroot;
            }
            count--;
        }

        private boolean inside(int x, int y) {
            return x >= 0 && x < M && y >= 0 && y < N;
        }
    }

    private int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UnionFindIsland islands = new UnionFindIsland(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                for (int[] d : dir) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (islands.inside(x, y) && grid[x][y] != '0') {
                        islands.union(i * n + j, x * n + y);
                    }
                }
            }
        }
        return islands.count;
    }

    public static void main(String[] args) {
        NumberOfIslands test = new NumberOfIslands();
        char[][] grid = new char[][]{{'1'}, {'1'}};
        System.out.print(test.numIslands(grid));
    }
}
