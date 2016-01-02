package leetcode;

/**
 * Created by fan on 7/7/15.
 */
public class FindPeakElement {

    public int findPeakElement(int[] num) {
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (num[mid] < num[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private void test(int... num) {
        int index = findPeakElement(num);
        System.out.println("peak element: " + num[index]);
    }

    public static void main(String[] args) {
        FindPeakElement test = new FindPeakElement();
        test.test(1, 2, 3, 4, 5);
        test.test(1, 11, 9, 8, 4, 4, 2);
    }
}
