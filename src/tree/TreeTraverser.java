package tree;

import java.util.*;

/**
 * Created by fan on 7/6/15.
 */
public abstract class TreeTraverser<T> {

    public abstract Iterable<T> children(T root);

    public final Iterable<T> preOderTraversal(final T root){
        checkNotNull(root);
        return () -> preOrderIterator(root);
    }

    Iterator<T> preOrderIterator(T root){
        return new PreOrderIterator(root);
    }

    private final class PreOrderIterator implements Iterator<T> {
        private final Deque<Iterator<T>> stack;

        PreOrderIterator(T root){
            this.stack = new ArrayDeque<>();
            stack.addLast(Collections.singletonList(checkNotNull(root)).iterator());
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            Iterator<T> itr = stack.getLast();
            T result = checkNotNull(itr.next());
            if(!itr.hasNext()){
                stack.removeLast();
            }
            Iterator<T> childItr = children(result).iterator();
            if(childItr.hasNext()){
                stack.addLast(childItr);
            }
            return result;
        }
    }

//    public final Iterable<T> postOrderTraversal(final T root){
//        checkNotNull(root);
//        return () -> postOrderIterator(root);
//    }
//
//    Iterator<T> postOrderIterator(T root){
//        return new PostOrderIterator(root);
//    }
//
//    private static final class PostOrderNode<T> {
//        final T root;
//        final Iterator<T> childIterator;
//
//        PostOrderNode(T root, Iterator<T> childIterator) {
//            this.root = checkNotNull(root);
//            this.childIterator = checkNotNull(childIterator);
//        }
//    }
//
//    private static final class PostOrderIterator implements Iterator<T>{
//        private final ArrayDeque<PostOrderNode<T>> stack;
//
//        PostOrderIterator(T root){
//            this.stack = new ArrayDeque<>();
//            stack.addLast(expand(root));
//        }
//
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public T next() {
//            return null;
//        }
//    }

    private static <T> T checkNotNull(T reference){
        if(reference == null){
            throw new RuntimeException();
        }
        return reference;
    }

}
