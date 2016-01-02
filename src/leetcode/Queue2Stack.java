package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by fan on 8/14/15.
 */
public class Queue2Stack<T> extends Stack<T>{
    Queue<T> queue = new LinkedList<>();

    public T push(T t){
        queue.offer(t);
        for (int i=0; i<queue.size()-1; i++){
            queue.offer(queue.poll());
        }
        return t;
    }

    public T pop(){
        return queue.poll();
    }

    public T peek(){
        return queue.peek();
    }
}
