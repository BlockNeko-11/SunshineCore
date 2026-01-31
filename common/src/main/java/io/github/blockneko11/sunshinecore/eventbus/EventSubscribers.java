package io.github.blockneko11.sunshinecore.eventbus;

import io.github.blockneko11.sunshinecore.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class EventSubscribers<T extends Event> {
    private final List<EventSubscriber<T>> delegate = Collections.synchronizedList(new ArrayList<>());

    void add(EventSubscriber<T> listener) {
        this.delegate.add(listener);
    }

    void remove(EventSubscriber<T> listener) {
        this.delegate.remove(listener);
    }

    void post(T event, ExceptionHandler handler) {
        this.delegate.sort(EventSubscriber::compare);
        this.delegate.forEach(listener -> listener.handle(event, handler));
    }
}
