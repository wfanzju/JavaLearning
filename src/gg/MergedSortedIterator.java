package gg;

import java.util.*;

/**
 * Created by fan on 10/9/15.
 */
public class MergedSortedIterator<T> implements Iterator<T> {
    private final Queue<PeekingIterator<T>> queue;

    public MergedSortedIterator(Iterable<? extends Iterator<? extends T>> iterators,
                                final Comparator<? super T> itemComparator) {

        Comparator<PeekingIterator<T>> heapComparator =
                (i1, i2) -> itemComparator.compare(i1.peek(), i2.peek());

        queue = new PriorityQueue<>(2, heapComparator);

        for (Iterator<? extends T> iterator : iterators) {
            if (iterator.hasNext()) {
                queue.add(new PeekingIterator<>(iterator));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        PeekingIterator<T> nextIter = queue.remove();
        T next = nextIter.next();
        if (nextIter.hasNext()) {
            queue.add(nextIter);
        }
        return next;
    }
}