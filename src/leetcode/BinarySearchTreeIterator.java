package leetcode;

import helper.TreeNode;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by fan on 7/9/15.
 */

public class BinarySearchTreeIterator implements Iterator<TreeNode>{

    private final Stack<TreeNode> stack;
    private TreeNode current;

    public BinarySearchTreeIterator(TreeNode root){
        current = root;
        stack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || current != null;
    }

    @Override
    public TreeNode next() {
        while(current != null){
            stack.push(current);
            current = current.getLeft();
        }
        TreeNode t = stack.pop();
        current = t.getRight();
        return t;
    }
}
