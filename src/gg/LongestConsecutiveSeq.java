package gg;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fan on 11/10/15.
 */
public class LongestConsecutiveSeq {
    public static int findArr(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int pre = nums[0];
        int maxLen = 0;
        int currLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == pre + 1) {
                currLen++;
            } else {
                currLen = 1;
            }
            maxLen = Math.max(maxLen, currLen);
            pre = nums[i];
        }
        return maxLen;
    }

    private int maxLen = 0;

    public int findTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfsTree(root, 1);
        return maxLen;
    }

    private void dfsTree(TreeNode root, int currLen) {
        if (root == null) {
            return;
        }
        maxLen = Math.max(maxLen, currLen);
        if (root.getLeft() != null
                && root.getLeft().getVal() == root.getVal() + 1) {
            dfsTree(root.getLeft(), currLen + 1);
        } else {
            dfsTree(root.getLeft(), 1);
        }
        if (root.getRight() != null
                && root.getRight().getVal() == root.getVal() + 1) {
            dfsTree(root.getRight(), currLen + 1);
        } else {
            dfsTree(root.getRight(), 1);
        }
    }

    private class V {
        int val;
    }

    Map<V, List<V>> adjMatrix = new HashMap<>();

    public int findGraph() {
        // topoSort O(V + E)
        List<V> topoList = new ArrayList<>();
        Map<V, Integer> lisMap = adjMatrix.keySet().stream().collect(Collectors.toMap(x -> x, x -> 1));
        for (V v : topoList) {
            for (V w : adjMatrix.get(v)) {
                if (w.val == v.val + 1) {
                    int maxLen = Math.max(lisMap.get(w), lisMap.get(v) + 1);
                    lisMap.put(w, maxLen);
                }
            }
        }
        return lisMap.values().stream().max(Integer::compare).orElse(1);
    }
}
