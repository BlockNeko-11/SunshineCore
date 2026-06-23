package io.github.blockneko11.sunshinecore.event.api.internals;

import io.github.blockneko11.sunshinecore.event.api.Event;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@ApiStatus.Internal
public final class EventImpl<T> implements Event<T> {
    private final Function<List<T>, T> invokerFunction;
    private final List<T> handlers = new ArrayList<>();

    private T invoker = null;

    public EventImpl(Function<List<T>, T> invokerFunction) {
        this.invokerFunction = invokerFunction;
    }

    @Override
    public void register(T handler) {
        this.handlers.add(handler);
        this.reset();
    }

    @Override
    public void unregister(T handler) {
        this.handlers.remove(handler);
        this.reset();
    }

    @Override
    public void clear() {
        this.handlers.clear();
        this.reset();
    }

    private void reset() {
        this.invoker = null;
    }

    @Override
    public T invoker() {
        if (this.invoker == null) {
            this.invoker = this.invokerFunction.apply(this.handlers);
        }

        return this.invoker;
    }
}
