package gg;

import java.util.Iterator;

/**
 * Created by fan on 10/9/15.
 */
class PeekingIterator<T> implements Iterator<T> {

    private final Iterator<? extends T> iterator;
    private boolean hasPeeked;
    private T peeked;

    public PeekingIterator(Iterator<? extends T> iter) {
        this.iterator = iter;
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasPeeked) {
            return iterator.next();
        }
        T result = peeked;
        hasPeeked = false;
        peeked = null;
        return result;
    }

    public T peek() {
        if (!hasPeeked) {
            peeked = iterator.next();
            hasPeeked = true;
        }
        return peeked;
    }

    @Override
    public void remove() {
        if (hasPeeked) {
            throw new IllegalStateException("Cannot remove after you've peeked at next");
        }
        iterator.remove();
    }
}