package gg;

import java.util.*;

/**
 * Created by fan on 7/26/15.
 */
public class RoundRobinIterator<T> implements Iterator<T> {

    private final Iterable<? extends Iterator<? extends T>> iterators;
    private Iterator<? extends Iterator<? extends T>> currIter;
    private Optional<T> nextVal;

    public RoundRobinIterator(Iterable<? extends Iterator<? extends T>> iterators) {
        this.iterators = iterators;
        this.currIter = iterators.iterator();
        this.nextVal = Optional.empty();
    }

    @Override
    public boolean hasNext() {
        update();
        return nextVal.isPresent();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T res = nextVal.get();
        nextVal = Optional.empty();
        return res;
    }

    private void update() {
        while (!nextVal.isPresent() && iterators.iterator().hasNext()) {
            if (!currIter.hasNext()) {
                currIter = iterators.iterator();
            }
            Iterator<? extends T> innerIter = currIter.next();
            if (innerIter.hasNext()) {
                nextVal = Optional.ofNullable(innerIter.next());
            } else {
                currIter.remove();
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> a = new LinkedList<>(Arrays.asList(1, 4, 7, null));
        List<Integer> b = new LinkedList<>(Arrays.asList(2, 5));
        List<Integer> c = new LinkedList<>();
        List<Integer> d = new LinkedList<>(Arrays.asList(3, 6, 8, 9, 10));
        List<Integer> e = new LinkedList<>();

        List<Iterator<Integer>> iterators = new LinkedList<>(
                Arrays.asList(a.iterator(), b.iterator(), c.iterator(),
                        d.iterator(), e.iterator()));
        RoundRobinIterator<Integer> testIter = new RoundRobinIterator<>(iterators);

        while (testIter.hasNext()) {
            System.out.print(testIter.next());
        }
    }
}
