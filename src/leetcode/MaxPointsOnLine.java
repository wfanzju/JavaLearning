package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 11/21/15.
 */
public class MaxPointsOnLine {
    class Point{
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int maxPoints(Point[] points) {
        if (points.length < 3) {
            return points.length;
        }
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> lineCount = new HashMap<>();
            int samex = 1;
            int samep = 0;
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    samep++;
                }else if (points[i].x == points[j].x) {
                    samex++;
                    continue;
                }
                double k = (points[j].y - points[i].y) * 1.0 / (points[j].x - points[i].x);
                lineCount.putIfAbsent(k, 1);
                res = Math.max(res, lineCount.get(k) + samep);
            }
            res = Math.max(res, samex);
        }
        return res;
    }
}
