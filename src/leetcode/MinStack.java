package leetcode;

import java.util.Stack;

/**
 * Created by fan on 9/8/15.
 */
public class MinStack extends Stack<Integer> {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    @Override
    public Integer push(Integer x) {
        stack.push(x);
        if (minStack.isEmpty() || x.compareTo(minStack.peek()) <= 0) {
            minStack.push(x);
        }
        return x;
    }

    @Override
    public Integer pop() {
        Integer toRet = stack.pop();
        if (toRet.equals(minStack.peek())) {
            minStack.pop();
        }
        return toRet;
    }

    @Override
    public Integer peek() {
        return stack.peek();
    }

    public Integer getMin() {
        return minStack.peek();
    }
}
