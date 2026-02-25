package io.github.blockneko11.sunshinecore.event.api;

import java.util.List;
import java.util.function.Function;

public interface Event<T> {
    void register(T handler);

    void unregister(T handler);

    boolean isRegistered(T handler);

    T invoker();

    static <T> Event<T> create(Function<List<T>, T> invokerFunction) {
        return new EventImpl<>(invokerFunction);
    }
}
