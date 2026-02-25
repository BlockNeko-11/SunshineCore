package io.github.blockneko11.sunshinecore.event.api;

import io.github.blockneko11.sunshinecore.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Deprecated(since = "1.3.0")
class Subscribers<T extends Event> {
    private final List<Subscriber<T>> delegate = Collections.synchronizedList(new ArrayList<>());

    void add(Subscriber<T> listener) {
        this.delegate.add(listener);
    }

    void remove(Subscriber<T> listener) {
        this.delegate.remove(listener);
    }

    void post(T event, ExceptionHandler handler) {
        this.delegate.sort(Subscriber::compare);
        this.delegate.forEach(listener -> listener.handle(event, handler));
    }
}
