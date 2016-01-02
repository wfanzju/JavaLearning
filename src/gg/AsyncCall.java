package gg;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by fan on 10/31/15.
 */
interface Machine {
}

interface FileChunk {
    int getFileId();
}

class PartialRes {
    Machine m;
    FileChunk f;
    Long sum;

    PartialRes(Machine m, FileChunk f, Long sum) {
        this.m = m;
        this.f = f;
        this.sum = sum;
    }
}

class Master {
    List<Machine> machines;
    List<FileChunk> fileChunks;

    public Long getSum() {
        ExecutorService service = Executors.newFixedThreadPool(machines.size());
        BlockingQueue<Future<PartialRes>> results = new LinkedBlockingQueue<>();
        Iterator<FileChunk> fileChunkIter = fileChunks.iterator();
        Map<Integer, AtomicLong> sums = new HashMap<>();
        for (FileChunk f : fileChunks) {
            sums.putIfAbsent(f.getFileId(), new AtomicLong(0));
        }
        for (Machine m : machines) {
            results.add(service.submit(() -> new Worker(m, fileChunkIter.next()).getSum()));
        }
        while (fileChunkIter.hasNext()) {
            try {
                Future<PartialRes> r = results.take();
                sums.get(r.get().f.getFileId()).getAndAdd(r.get().sum);
                results.add(service.submit(() -> new Worker(r.get().m, fileChunkIter.next()).getSum()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Long total = 0L;
        for (AtomicLong a : sums.values()) {
            total += a.get();
        }
        service.shutdown();
        return total;
    }
}

class Worker {
    Machine m;
    FileChunk f;

    Worker(Machine m, FileChunk f) {
        this.m = m;
        this.f = f;
    }

    PartialRes getSum() {
        try {
            Random r = new Random();
            TimeUnit.SECONDS.sleep(r.nextInt(5));
            return new PartialRes(m, f, r.nextLong());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class AsyncCall {
}
