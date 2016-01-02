package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 10/25/15.
 */

public class MaxSumOfTreeNodes {
    class nTreeNode {
        int val;
        List<nTreeNode> children = new ArrayList<>();

        public nTreeNode(int v) {
            val = v;
        }
    }

    public int maxSumRec(nTreeNode root) {
        int sum = 0;
        if (root.children.isEmpty()) {
            root.val = 10;
        } else {
            for (nTreeNode node : root.children) {
                sum += maxSumRec(node);
            }
            for (int i = 10; i >= 1; i--) {
                boolean conflict = false;
                for (nTreeNode node : root.children) {
                    if (node.val == i) {
                        conflict = true;
                        break;
                    }
                }
                if (!conflict) {
                    root.val = i;
                }
            }
        }
        sum += root.val;
        return sum;
    }
}
