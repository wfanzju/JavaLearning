package leetcode;

import java.util.Stack;

/**
 * Created by fan on 9/16/15.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> matchStack = new Stack<>();
        matchStack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                matchStack.push(i);
            } else if (s.charAt(i) == ')') {
                matchStack.pop();
                if (matchStack.isEmpty()) {
                    matchStack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - matchStack.peek());
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return maxLen;
    }
}
