package io.github.blockneko11.sunshinecore.event.api;

import io.github.blockneko11.sunshinecore.event.api.handlers.EventResultDelegate;
import io.github.blockneko11.sunshinecore.event.api.handlers.InteractionResultDelegate;
import io.github.blockneko11.sunshinecore.event.api.handlers.NonReturnDelegate;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public final class EventFactory {
    public static <T> Event<T> create(Function<List<T>, T> invokerFunction) {
        return new EventImpl<>(invokerFunction);
    }

    @SafeVarargs
    public static <T> Event<T> createWithInteractionResult(@Nullable T... typeGetter) {
        return (Event<T>) createWithInteractionResult(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createWithInteractionResult(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new InteractionResultDelegate<>(handlers)));
    }

    public static <T> Event<T> createWithEventResult(@Nullable T... typeGetter) {
        return (Event<T>) createWithEventResult(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createWithEventResult(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new EventResultDelegate<>(handlers)));
    }

    @SafeVarargs
    public static <T> Event<T> createNonReturn(@Nullable T... typeGetter) {
        return (Event<T>) createNonReturn(typeGetter.getClass().getComponentType());
    }

    public static <T> Event<T> createNonReturn(Class<T> handlerClass) {
        return create(handlers -> (T) Proxy.newProxyInstance(handlerClass.getClassLoader(),
                new Class[]{handlerClass},
                new NonReturnDelegate<>(handlers)));
    }

    private EventFactory() {
    }
}
