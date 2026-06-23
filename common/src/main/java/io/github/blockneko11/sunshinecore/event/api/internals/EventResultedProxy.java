package io.github.blockneko11.sunshinecore.event.api.internals;

import io.github.blockneko11.sunshinecore.event.api.EventResult;
import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

@ApiStatus.Internal
public class EventResultedProxy<T> implements InvocationHandler {
    private final List<T> handlers;

    public EventResultedProxy(List<T> handlers) {
        this.handlers = handlers;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (T handler : this.handlers) {
            Object result = method.invoke(handler, args);

            if (result != EventResult.PASS) {
                return result;
            }
        }

        return EventResult.PASS;
    }
}
