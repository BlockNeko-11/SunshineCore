package io.github.blockneko11.sunshinecore.event.api.handlers;

import java.lang.reflect.InvocationHandler;
import java.util.List;

public abstract class EventDelegate<T> implements InvocationHandler {
    protected final List<T> handlers;

    protected EventDelegate(List<T> handlers) {
        this.handlers = handlers;
    }
}
