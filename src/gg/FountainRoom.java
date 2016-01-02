package gg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by fan on 10/30/15.
 */
public class FountainRoom {
    private static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int m;
    private int n;

    public int[] findBestPlace(int[][] room) {
        m = room.length;
        n = room[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (room[i][j] == Integer.MIN_VALUE) {
                    bfsShortestDistance(room, i, j);
                }
            }
        }
        int[] res = new int[2];
        int distanceSum = Integer.MAX_VALUE;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j] != Integer.MAX_VALUE
                        && room[i][j] != Integer.MIN_VALUE
                        && room[i][j] != 0) {
                    if (room[i][j] < distanceSum) {
                        distanceSum = room[i][j];
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        System.out.println("shortestDistance:" + distanceSum);
        System.out.println("bestPlace:" + Arrays.toString(res));
        return res;
    }

    private void bfsShortestDistance(int[][] room, int x, int y) {
        int[][] newRoom = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isEmpty(room, i, j)) {
                    newRoom[i][j] = 0;
                } else {
                    newRoom[i][j] = room[i][j];
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int i = pos[0];
            int j = pos[1];
            int dist = (newRoom[i][j] == Integer.MIN_VALUE) ? 1 : newRoom[i][j] + 1;
            for (int[] d : dir) {
                int ii = i + d[0];
                int jj = j + d[1];
                if (isEmpty(newRoom, ii, jj) && setShorter(newRoom, ii, jj, dist)) {
                    q.offer(new int[]{ii, jj});
                }
            }
        }
        for (int[] row : newRoom) {
            System.out.println(Arrays.toString(row));
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isEmpty(room, i, j)) {
                    room[i][j] += newRoom[i][j];
                }
            }
        }
    }

    private boolean isEmpty(int[][] room, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n
                && room[x][y] != Integer.MAX_VALUE
                && room[x][y] != Integer.MIN_VALUE;
    }

    private boolean setShorter(int[][] room, int x, int y, int dist) {
        if (room[x][y] == 0 || dist < room[x][y]) {
            room[x][y] = dist;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] room = new int[5][5];
        room[1][1] = Integer.MAX_VALUE;
        room[1][2] = Integer.MIN_VALUE;
        room[2][2] = Integer.MAX_VALUE;
        room[3][3] = Integer.MIN_VALUE;
        FountainRoom test = new FountainRoom();
        test.findBestPlace(room);
    }
}
