package io.github.blockneko11.sunshinecore.event.api;

public interface Event<T> {
    void register(T handler);

    void unregister(T handler);

    void clear();

    T invoker();
}
