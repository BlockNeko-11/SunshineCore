package io.github.blockneko11.sunshinecore.event.api.internals;

import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

@ApiStatus.Internal
public final class VoidProxy<T> implements InvocationHandler {
    private final List<T> handlers;

    public VoidProxy(List<T> handlers) {
        this.handlers = handlers;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (T handler : this.handlers) {
            method.invoke(handler, args);
        }

        return null;
    }
}
