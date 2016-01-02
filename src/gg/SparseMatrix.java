package gg;

/**
 * Created by fan on 11/19/15.
 */
public class SparseMatrix<T> {
    private class Node {
        public int row;
        public int col;
        public T val;
        public Node nextInRow;
        public Node nextInCol;

        public Node(int row, int col, T val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    private Node corner;
    private int numRows;
    private int numCols;

    public SparseMatrix(int rowSize, int colSize) {
        numRows = rowSize;
        numCols = colSize;
        corner = new Node(0, 0, null);
        corner.nextInRow = new Node(0, colSize + 1, null);
        corner.nextInCol = new Node(rowSize + 1, 0, null);
    }

    public T getValue(int r, int c) {
        Node rowHead = findRow(r);
        T res = null;
        if (rowHead != null) {
            Node target = searchInRow(rowHead, c);
            if (target != null) {
                res = target.val;
            }
        }
        return res;
    }

    public void setValue(int r, int c, T newVal) {
        if (newVal == null) {
            //delete
        } else {
            //insert
        }
    }

    private Node findRow(int r) {
        Node iter = corner;
        Node res = null;
        while (iter.row < r) {
            iter = iter.nextInCol;
        }
        if (iter.row == r) {
            res = iter;
        }
        return res;
    }

    private Node searchInRow(Node rowHead, int c) {
        Node iter = rowHead;
        Node res = null;
        while (iter.col < c) {
            iter = iter.nextInRow;
        }
        if (iter.col == c) {
            res = iter;
        }
        return res;
    }
}
