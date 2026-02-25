package io.github.blockneko11.sunshinecore.event.api;

import java.lang.reflect.Proxy;
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

    static <T> Event<T> create(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new ReflectiveInvoker<>(handlers)));
    }
}
