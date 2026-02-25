package io.github.blockneko11.sunshinecore.event;

/**
 * The base event class with cancellation support.
 */
@Deprecated(since = "1.3.0")
public abstract class CancellableEvent extends Event {
    private boolean cancelled;

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
