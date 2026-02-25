package io.github.blockneko11.sunshinecore.event.api;

import io.github.blockneko11.sunshinecore.event.Event;

@Deprecated(since = "1.3.0")
@FunctionalInterface
public interface ExceptionHandler {
    void handle(Class<? extends Event> type, Exception ex);
}
