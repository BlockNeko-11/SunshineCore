package io.github.blockneko11.sunshinecore.event.api;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public final class EventFactory {
    public static <T> Event<T> create(Function<List<T>, T> invokerFunction) {
        return new EventImpl<>(invokerFunction);
    }

    @SafeVarargs
    public static <T> Event<T> createWithResult(T... typeGetter) {
        return (Event<T>) createWithResult(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createWithResult(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new InteractionResultReflectiveInvoker<>(handlers)));
    }

    @SafeVarargs
    public static <T> Event<T> createNonReturn(T... typeGetter) {
        return (Event<T>) createNonReturn(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createNonReturn(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new VoidReflectiveInvoker<>(handlers)));
    }

    private EventFactory() {
    }
}
