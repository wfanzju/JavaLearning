package gg;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by fan on 11/18/15.
 */
public class NestedIterator<T> implements Iterator<T> {

    private final Stack<Iterator> iteratorStack;

    private T next;
    private boolean nextValid = false;

    private Class<T> type;

    public NestedIterator(Iterable colletion, Class<T> type) {
        iteratorStack = new Stack<>();
        iteratorStack.push(colletion.iterator());
        this.type = type;
    }

    @Override
    public boolean hasNext() {
        if (!nextValid) {
            update();
        }
        return nextValid;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        nextValid = false;
        return next;
    }

    private void update() {
        while (!iteratorStack.empty()) {
            if (!iteratorStack.peek().hasNext()) {
                iteratorStack.pop();
                continue;
            }
            final Object peek = iteratorStack.peek().next();
            if (type.isInstance(peek)) {
                next = (T) peek;
                nextValid = true;
                return;
            }else if (peek instanceof Iterable) {
                iteratorStack.push(((Iterable) peek).iterator());
            } else {
                throw new RuntimeException("Unknown list element");
            }
        }
    }

    public static void main(String[] args) {
        NestedIterator<Integer> iterator =
                new NestedIterator<>(Arrays.asList(1,2, Arrays.asList(3,4)), Integer.class);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
