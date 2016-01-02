package gg;

import java.util.Arrays;

/**
 * Created by fan on 10/18/15.
 */
public class Read4K {
    private char[] buffer = new char[4];
    private int bufSize = 0;
    private int offset = 0;

    public int read(char[] res, int n) {
        int currIndex = 0;
        boolean eof = false;
        while (!eof && currIndex < n) {
            if (bufSize == 0) {
                bufSize = read4k(buffer);
                if (bufSize < 4) {
                    eof = true;
                }
            }
            int bytes = Math.min(n - currIndex, bufSize);
            // System.arraycopy(buffer, offset, res, currIndex, bytes);
            for (int i = 0; i < bytes; i++) {
                res[currIndex++] = buffer[offset++];
            }
            offset %= 4;
            bufSize -= bytes;
        }
        return currIndex;
    }

    private int read4k(char[] buf) {
        for (int i = 0; i < 4; i++) {
            buf[i] = (char) ('1' + i);
        }
        return 4;
    }

    public static void main(String[] args) {
        Read4K test = new Read4K();
        char[] arr = new char[10];
        int res = test.read(arr, 10);
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
        char[] arr2 = new char[1];
        res = test.read(arr2, 1);
        System.out.println(Arrays.toString(arr2));
    }
}
