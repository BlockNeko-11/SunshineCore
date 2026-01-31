package io.github.blockneko11.sunshinecore.eventbus;

import io.github.blockneko11.sunshinecore.event.Event;

@FunctionalInterface
public interface ExceptionHandler {
    void handle(Class<? extends Event> type, Exception ex);
}
