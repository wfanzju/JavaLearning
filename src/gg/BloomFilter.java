package gg;

import java.util.BitSet;

/**
 * Created by fan on 11/10/15.
 */
public class BloomFilter {
    private static final int DEFAULT_SIZE = 1 << 25;
    private static final int[] seeds = new int[]{5, 7, 11, 13};
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] hashs = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            hashs[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    public void add(String s) {
        for (SimpleHash h : hashs) {
            bits.set(h.hash(s), true);
        }
    }

    public boolean contains(String s) {
        if (s == null) {
            return false;
        }
        for (SimpleHash h : hashs) {
            if (!bits.get(h.hash(s))) {
                return false;
            }
        }
        return true;
    }

    static class SimpleHash {
        private int cap;
        private int seed;

        SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                res = res * seed + s.charAt(i);
            }
            return (cap - 1) & res;
        }
    }
}
