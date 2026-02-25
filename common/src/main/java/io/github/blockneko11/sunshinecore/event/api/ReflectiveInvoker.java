package io.github.blockneko11.sunshinecore.event.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

final class ReflectiveInvoker<T> implements InvocationHandler {
    private final List<T> handlers;

    public ReflectiveInvoker(List<T> handlers) {
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
