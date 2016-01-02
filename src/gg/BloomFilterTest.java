package gg;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by fan on 11/10/15.
 */
public class BloomFilterTest {
    @Test
    public void test() {
        BloomFilter bf = new BloomFilter();
        bf.add("");
        assertTrue(bf.contains(""));
    }
}
