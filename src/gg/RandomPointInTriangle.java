package gg;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by fan on 11/7/15.
 */
public class RandomPointInTriangle {
    private static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }
        @Override
        public int hashCode() {
            final int prime = 17;
            int res = 1;
            res = res * prime + Double.hashCode(x);
            res = res * prime + Double.hashCode(y);
            return res;
        }
    }

    private static Random random = new Random();

    public static Point randomPoint(Point[] tri) {
        Point edge1 = new Point(tri[1].x - tri[0].x, tri[1].y - tri[0].y);
        Point edge2 = new Point(tri[2].x - tri[0].x, tri[2].y - tri[0].y);
        double sum = random.nextDouble();
        double d1 = random.nextDouble() * sum;
        double d2 = sum - d1;
        return new Point(edge1.x * d1 + edge2.x * d2 + tri[0].x, edge1.y * d1 + edge2.y * d2 + tri[0].y);
    }

    public static boolean checkSquare(Point[] points) {
        double x = 0;
        double y = 0;
        for (Point p : points) {
            x += p.x;
            y += p.y;
        }
        Point center = new Point(x / 4, y / 4);
        Set<Point> vectors = new HashSet<>();
        for (Point p : points) {
            vectors.add(new Point(p.x - center.x, p.y - center.y));
        }
        if (vectors.size() != 4) {
            return false;
        }
        Point p = vectors.iterator().next();
        Point p1 = new Point(-p.x, -p.y);
        Point p2 = new Point(p.y, -p.x);
        Point p3 = new Point(-p.y, p.x);
        return vectors.contains(p1)
                && vectors.contains(p2)
                && vectors.contains(p3);
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(-1, 1);
        Point p3 = new Point(1, -1);
        Point p4 = new Point(-1, -1);
        System.out.println(checkSquare(new Point[]{p1, p2, p3, p4}));
    }
}
