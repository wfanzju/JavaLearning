package amazon;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by fan on 8/12/15.
 */

public class DipScan {
    private Iterable<Consumer<String>> scanDataSource() {
        Stream<Consumer<String>> dropletStream = Arrays.asList(1, 2)
                .stream()
                .map(i -> (s -> System.out.println(s + "with id:" + i)));
        Stream<Consumer<String>> dropletClassStream = Arrays.asList(false, true)
                .stream()
                .map(b -> (s -> System.out.println(s + "with status available:" + b)));
        return Stream.concat(dropletStream, dropletClassStream)::iterator;
    }

    public static void main(String[] args) {
        String dropletInfoCache = "now updating dropletInfoCache from droplet ";
        new DipScan().scanDataSource().forEach(c -> c.accept(dropletInfoCache));
        new DipScan().getItemsToScan("test-scan-id").forEach(t -> {
            try {
                t.updateCache("dropletInfoCache", "test-metrics");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Iterable<CacheUpdateTask> getItemsToScan(String scanId) {
        Stream<CacheUpdateTask> streamFromDroplet = Arrays.asList(1, 2)
                .stream()
                .map(dip -> insertCacheWithDroplet(scanId, dip));
        return streamFromDroplet::iterator;
    }

    private static CacheUpdateTask insertCacheWithDroplet(String scanId, int dip) {
        return new CacheUpdateTask() {
            @Override
            public void updateCache(String dropletInfoCache, String metrics) throws Exception {
                boolean success = false;
                try {
                    System.out.println(String.format(
                            "Insert %s with scanId=%s and dip=%d", dropletInfoCache, scanId, dip
                    ));
                    success = true;
                } finally {
                    System.out.println(String.format(
                            "Adding succeeded metrics %s: %b", metrics, success
                    ));
                }
            }
        };
    }
}

interface CacheUpdateTask {
    public void updateCache(String dropletInfoCache, String metrics) throws Exception;
}