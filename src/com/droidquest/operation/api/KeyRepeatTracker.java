package com.droidquest.operation.api;

/**
 * Counter class which can track the key repeat delay.
 */
public class KeyRepeatTracker {
    private final long delayMillis;
    private long startingTimestamp;

    /**
     * Create the repeat tracker.
     * @param delayMillis the delay in milliseconds, before we allow "repeats" to occur.
     */
    public KeyRepeatTracker(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public void start() {
        startingTimestamp = System.currentTimeMillis();
    }

    public void reset() {
        startingTimestamp = 0L;
    }

    public boolean isStarted() {
        return startingTimestamp != 0;
    }

    public boolean isRepeating() {
        return (System.currentTimeMillis() - startingTimestamp) > delayMillis;
    }
}
