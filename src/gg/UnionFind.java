package gg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by fan on 10/27/15.
 */
public class UnionFind {
    Map<Integer, Integer> preMap = new HashMap<>();
    Set<Integer> roots = new HashSet<>();

    public int find(int x) {
        int root = x;
        while (preMap.get(root) != root) {
            root = preMap.get(root);
        }
        // compression
        int curr = x;
        while (curr != root) {
            int tmp = preMap.get(curr);
            preMap.put(curr, root);
            curr = tmp;
        }
        return root;
    }

    public void join(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            preMap.put(rootx, rooty);
        }
    }

    public void initialize(int x) {
        preMap.put(x, x);
    }

    public int getUnionCount() {
        for (int k : preMap.keySet()) {
            roots.add(find(k));
        }
        return roots.size();
    }
}
