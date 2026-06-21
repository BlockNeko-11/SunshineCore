package io.github.blockneko11.sunshinecore.event.api;

public interface Event<T> {
    void register(T handler);

    T invoker();
}
