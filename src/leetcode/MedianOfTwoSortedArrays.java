package leetcode;

/**
 * Created by fan on 10/31/15.
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int k = (m + n) / 2;
        int m1 = findKth(A, 0, m, B, 0, n, k + 1);
        if ((m + n) % 2 == 0) {
            int m2 = findKth(A, 0, m, B, 0, n, k);
            return (m1 + m2) / 2.0;
        }
        return m1;
    }

    private int findKth(int[] A, int as, int aLen, int[] B, int bs, int bLen, int k) {
        if (aLen < bLen) {
            return findKth(B, bs, bLen, A, as, aLen, k);
        }
        if (bLen == 0) {
            return A[as + k - 1];
        }
        if (k == 1) {
            return Math.min(A[as], B[bs]);
        }

        int bmid = Math.min(bLen, k / 2);
        int amid = k - bmid;
        if (A[as + amid - 1] > B[bs + bmid - 1]) {
            return findKth(A, as, amid, B, bs + bmid, bLen - bmid, k - bmid);
        }
        return findKth(A, as + amid, aLen - amid, B, bs, bmid, k - amid);
    }
}
