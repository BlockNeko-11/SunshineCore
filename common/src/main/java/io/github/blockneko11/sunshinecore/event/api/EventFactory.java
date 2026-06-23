package io.github.blockneko11.sunshinecore.event.api;

import io.github.blockneko11.sunshinecore.event.api.internals.EventImpl;
import io.github.blockneko11.sunshinecore.event.api.internals.EventResultedProxy;
import io.github.blockneko11.sunshinecore.event.api.internals.InteractionResultedProxy;
import io.github.blockneko11.sunshinecore.event.api.internals.VoidProxy;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public final class EventFactory {
    public static <T> Event<T> create(Function<List<T>, T> invokerFunction) {
        return new EventImpl<>(invokerFunction);
    }

    @SafeVarargs
    public static <T> Event<T> createWithInteractionResult(T... typeGetter) {
        return (Event<T>) createWithInteractionResult(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createWithInteractionResult(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new InteractionResultedProxy<>(handlers)));
    }

    public static <T> Event<T> createWithEventResult(T... typeGetter) {
        return (Event<T>) createWithEventResult(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createWithEventResult(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new EventResultedProxy<>(handlers)));
    }

    @SafeVarargs
    public static <T> Event<T> createNonReturn(T... typeGetter) {
        return (Event<T>) createNonReturn(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createNonReturn(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new VoidProxy<>(handlers)));
    }

    private EventFactory() {
    }
}
