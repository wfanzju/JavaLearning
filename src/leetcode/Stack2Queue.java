package leetcode;

import java.util.Stack;

/**
 * Created by fan on 8/30/15.
 */
public class Stack2Queue<T> {

    Stack<T> input = new Stack<>();
    Stack<T> output = new Stack<>();

    public void push(T t) {
        input.push(t);
    }

    public T pop() {
        peek();
        return output.pop();
    }

    /*
     * Each element only ever gets moved once and only after pushing,
     * so the overall amortized cost for each operation is O(1).
     */
    public T peek() {
        if (output.empty()) {
            while (!input.empty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }

    public static void main(String[] args) {
        Stack2Queue<Integer> queue = new Stack2Queue<>();
        System.out.println(queue.empty());
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        queue.push(4);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
