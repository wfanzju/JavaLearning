package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 7/16/15.
 */
public class ReverseBits {

    public int reverseBits(int n) {
        int reversed = 0;
        for (int i = 0; i < 32; i++) {
            reversed |= n & 1;
            n >>>= 1;
            if (i < 31) {
                reversed <<= 1;
            }
        }
        return reversed;
    }

    public int divideAndConquer(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    /**
     * Optimization if this method will be called many times.
     * <p>
     * Divide an int into 4 bytes, and reverse each byte, then combine into an int.
     * For each byte, we can use cache to improve performance.
     */
    private final Map<Byte, Integer> cache = new HashMap<>();

    public int optimizedReverseBits(int n) {
        int reversed = 0;
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte) ((n >>> 8 * i) & 0xff);
            reversed |= reverseByte(bytes[i]);
            if (i < 3) {
                reversed <<= 8;
            }
        }
        return reversed;
    }

    private int reverseByte(byte b) {
        if (cache.containsKey(b)) {
            return cache.get(b);
        }
        int reversed = 0;
        for (int i = 0; i < 8; i++) {
            reversed = (reversed << 1) | ((b >>> i) & 1);
        }
        cache.put(b, reversed);
        return reversed;
    }
}
