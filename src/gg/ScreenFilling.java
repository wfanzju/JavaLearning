package gg;

/**
 * Created by fan on 11/9/15.
 */
public class ScreenFilling {
    public static int numOfStrFillScreen(String s, int m, int n) {
        String[] words = s.split("\\s+");
        int count = 0;
        int[] pos = new int[2];
        while (pos[0] < m) {
            addStrToScreen(words, pos, n);
            count++;
        }
        return Math.max(0, count - 1);
    }

    private static void addStrToScreen(String[] words, int[] pos, int n) {
        for (String word : words) {
            if (word.length() > n) {
                pos[0] = Integer.MAX_VALUE;
                return;
            }
            if (word.length() > n - pos[1]) {
                pos[1] = word.length() + 1;
                pos[0] += 1;
            } else {
                pos[1] += word.length() + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(numOfStrFillScreen("a bc", 2, 8));
    }
}
