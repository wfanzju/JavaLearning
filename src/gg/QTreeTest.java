package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 10/31/15.
 */
class QuadTree<Integer, V> {
    private QTNode root;

    public QuadTree(int maxX, int maxY) {
        this.root = new QTNode(null, 0, 0, maxX - 1, maxY - 1);
    }

    public void insert(int x, int y, V val) {
        root.insert(val, x, y);
    }

    public void print() {
        root.print();
    }

    public V getVal(int x, int y) {
        return null;
    }

    private class QTNode {
        int ax, ay;
        int bx, by;
        V val;
        List<QTNode> children;

        QTNode(V val, int ax, int ay, int bx, int by) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.val = val;
            children = new ArrayList<>();
        }

        boolean insert(V val, int x, int y) {
            if (!withinRange(x, y)) {
                return false;
            }
            if (ax == bx && ay == by) {
                this.val = val;
                return true;
            } else {
                subDivide();
                for (QTNode n : children) {
                    if (n.insert(val, x, y)) {
                        return true;
                    }
                }
                return false;
            }
        }

        boolean withinRange(int x, int y) {
            return x >= this.ax && x <= this.bx
                    && y >= this.ay && y <= this.by;
        }

        void subDivide() {
            if (children.size() != 0) {
                return;
            }
            int mx = (ax + bx) >> 1;
            int my = (ay + by) >> 1;
            // bottom left
            children.add(new QTNode(val, ax, ay, mx, my));
            // top left
            children.add(new QTNode(val, ax, my + 1, mx, by));
            // bottom right
            children.add(new QTNode(val, mx + 1, ay, bx, my));
            // top right
            children.add(new QTNode(val, mx + 1, my + 1, bx, by));
        }

        QTNode combine(QTNode qtNode) {
            if (val != null && qtNode.val != null) {
                this.val = qtNode.val;
                return this;
            }
            subDivide();
            qtNode.subDivide();
            for (int i = 0; i < 4; i++) {
                this.children.get(i).combine(qtNode.children.get(i));
            }
            return this;
        }

        void print() {
            System.out.println(String.format("ax:%d, ay:%d, bx:%d, by:%d, val:%s.", ax, ay, bx, by, val));
            for (QTNode n : children) {
                System.out.print("child:");
                n.print();
            }
        }
    }
}

enum Color {
    BLACK,
    WHITE
}

public class QTreeTest {
    public static QuadTree constructImage(boolean[][] image) {
        int m = image.length;
        int n = image[0].length;
        QuadTree<Integer, Color> qt = new QuadTree<>(m, n);
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                qt.insert(i, j, image[i][j] ? Color.BLACK : Color.WHITE);
            }
        }
        return qt;
    }

    public static void main(String[] args) {
        boolean[][] image = new boolean[2][3];
        QuadTree qt = constructImage(image);
        qt.print();
    }
}
