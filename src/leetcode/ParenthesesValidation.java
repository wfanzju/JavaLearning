package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by fan on 9/15/15.
 */
public class ParenthesesValidation {
    private static final Map<Character, Character> parenthesesMap =
            new HashMap<Character, Character>() {{
                put('(', ')');
                put('{', '}');
                put('[', ']');
            }};

    public boolean isValid(String s) {
        Stack<Character> matcherStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (parenthesesMap.containsKey(c)) {
                matcherStack.push(c);
            } else if (matcherStack.isEmpty()
                    || parenthesesMap.get(matcherStack.pop()) != c) {
                return false;
            }
        }
        return matcherStack.isEmpty();
    }
}
