package amazon;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fan on 11/27/15.
 */
public class Throttler {
    private final Map<String, TokenBucket> apiThrottleMap = new ConcurrentHashMap<>();

    public Throttler(List<String> throttleApis) {
        throttleApis.stream().forEach(s -> apiThrottleMap.put(s, new TokenBucket(100, 10)));
    }
}

class TokenBucket {
    private final long maxNumOfTokens;
    private final double newTokensPerSec;

    private long timeOfLastRefill;
    private volatile double numOfTokens;

    public TokenBucket(long maxTokens, double throttleRate) {
        if (maxTokens <= 0 || throttleRate <= 0) {
            throw new IllegalArgumentException("maxTokens and throttleRate must be > 0");
        }
        maxNumOfTokens = maxTokens;
        newTokensPerSec = throttleRate;

        numOfTokens = maxNumOfTokens;
        timeOfLastRefill = System.currentTimeMillis();
    }

    public synchronized boolean requestToken() {
        refillTokens();
        if (numOfTokens < 1) {
            return false;
        }
        --numOfTokens;
        return true;
    }

    public synchronized boolean haveTokens(int numTokensDesired) {
        refillTokens();
        return (numOfTokens >= numTokensDesired);
    }

    public synchronized void consumeTokens(long numTokens) {
        if (numTokens < 0) {
            throw new IllegalArgumentException("Cannot consume negative number of tokens");
        }
        numOfTokens -= numTokens;
    }

    private synchronized void refillTokens() {
        long now = System.currentTimeMillis();
        double tokensToAdd = (now - timeOfLastRefill) / 1000.0 * newTokensPerSec;
        numOfTokens = Math.max(numOfTokens, numOfTokens + tokensToAdd);
        numOfTokens = Math.min(numOfTokens, maxNumOfTokens);
        timeOfLastRefill = now;
    }

    public synchronized long nextExpectedTokenAvailable() {
        if (haveTokens(1)) {
            return 0;
        }
        double neededTokens = 1.0 - numOfTokens;
        return (long) (neededTokens / newTokensPerSec * 1000);
    }
}
