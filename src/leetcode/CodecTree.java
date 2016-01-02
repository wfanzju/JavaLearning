package leetcode;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by fan on 11/17/15.
 */
public class CodecTree {
    private static final String spliter = ",";
    private static final String NULL = "X";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    public TreeNode deserialize(String data) {
        List<String> nodes = new ArrayList<>(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(List<String> nodes) {
        String val = nodes.remove(0);
        if (NULL.equals(val)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        CodecTree test = new CodecTree();
        String s = test.serialize(root);
        System.out.println(s);
        TreeNode n = test.deserialize(s);
        System.out.println(n);
    }
}
