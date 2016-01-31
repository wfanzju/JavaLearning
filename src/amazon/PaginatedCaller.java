package amazon;

import javax.swing.text.html.Option;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by fan on 1/31/16.
 */
public class PaginatedCaller<V> implements Iterator<V> {

    private final Function<String, V> caller;
    private final Function<V, String> pageTokenFetcher;
    private Optional<String> lastPageToken;
    private Optional<V> queuedResponse;
    private boolean initCall = true;

    public PaginatedCaller(Function<String, V> caller, Function<V, String> pageTokenFetcher) {
        this.caller = caller;
        this.pageTokenFetcher = pageTokenFetcher;
        this.lastPageToken = Optional.empty();
        this.queuedResponse = Optional.empty();
    }

    @Override
    public boolean hasNext() {
        refreshQueuedResponse(initCall);
        if (initCall) {
            initCall = false;
        }
        return queuedResponse.isPresent();
    }

    @Override
    public V next() {
        if (hasNext()) {
            V res = queuedResponse.get();
            queuedResponse = Optional.empty();
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }

    public Stream<V> stream() {
        Iterable<V> iterable = () -> this;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    private void refreshQueuedResponse(boolean initCall) {
        if (queuedResponse.isPresent()) {
            return;
        }
        if (lastPageToken.isPresent() || initCall) {
            try {
                queuedResponse = Optional.ofNullable(caller.apply(lastPageToken.orElse(null)));
                lastPageToken = queuedResponse.map(pageTokenFetcher);
            } catch (Exception e) {
                lastPageToken = Optional.empty();
            }
        }
    }

    @Override
    public void remove() {
        throw new IllegalArgumentException("remove() is invalid for type PaginatedCaller.");
    }
}
