package gg;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fan on 11/15/15.
 */
public class LRUCacheProd<K, V> {
    private final LinkedHashMap<K, V> cache;
    private final int capacity;
    private static final float loadFactor = 0.75f;

    public LRUCacheProd(int c) {
        capacity = c;
        cache = new LinkedHashMap<K, V>(capacity, loadFactor, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public V get(K k) {
        return cache.get(k);
    }

    public void set(K k, V v) {
        cache.put(k, v);
    }
}
