package leetcode;

/**
 * Created by fan on 11/15/15.
 */
public class ExcelColToNum {
    public static int titleToNum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + s.charAt(i) - 'A' + 1;
        }
        return res;
    }

    public static String numToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.insert(0, (char) ('A' + (n - 1) % 26));
            n = (n - 1) / 26;
        }
        return res.toString();
    }

    public static String test(int n) {
        return n == 0 ? "" : test(n / 26) + (char) ('A' + n % 26);
    }

    public static void main(String[] args) {
        String test = "BA";
        int n = titleToNum(test);
        System.out.println(n);
        System.out.println(numToTitle(n));
        System.out.println(test(26));
    }
}
