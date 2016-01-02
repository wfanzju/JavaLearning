package gg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by fan on 11/11/15.
 */
public class MinStepToEndKJump {
    public static int minStepToEnd(int[][] room, int[] start, int[] end, int k) {
        boolean[][] visited = new boolean[room.length][room[0].length];
        int[][] nextPos = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] nextJumpPos = new int[][]{{2, 0}, {-2, 0}, {0, 2}, {0, -2}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1], k});
        int step = 0;
        while (!q.isEmpty()) {
            for (int i = 0; i < q.size(); i++) {
                int[] pos = q.poll();
                if (pos[0] == end[0] && pos[1] == end[1]) {
                    return step;
                }
                visited[pos[0]][pos[1]] = true;
                for (int[] n : nextPos) {
                    if (isValid(pos, n, room, visited)) {
                        q.add(new int[]{pos[0] + n[0], pos[1] + n[1], pos[2]});
                    }
                }
                if (pos[2] > 0) {
                    for (int[] n : nextJumpPos) {
                        if (isValid(pos, n, room, visited)) {
                            q.add(new int[]{pos[0] + n[0], pos[1] + n[1], pos[2] - 1});
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private static boolean isValid(int[] pos, int[] step, int[][] room, boolean[][] visited) {
        int m = room.length;
        int n = room[0].length;
        int i = pos[0] + step[0];
        int j = pos[1] + step[1];
        return i >= 0 && i < m && j >= 0 && j < n && !visited[i][j] && room[i][j] != 1;
    }

}
