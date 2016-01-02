package gg;

/**
 * Created by fan on 11/18/15.
 */
public class MaxTrianglePath {
    public int getMaxPath(int[][] data) {
        for (int l = data.length - 1; l > 0; l--) {
            for (int i = 0; i < data[l].length-1; i++) {
                data[l - 1][i] += Math.max(data[l][i], data[l][i + 1]);
            }
        }
        return data[0][0];
    }
}
