package io.github.blockneko11.sunshinecore.event.api.handlers;

import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.Method;
import java.util.List;

@ApiStatus.Internal
public final class NonReturnDelegate<T> extends EventDelegate<T> {
    public NonReturnDelegate(List<T> handlers) {
        super(handlers);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (T handler : this.handlers) {
            method.invoke(handler, args);
        }

        return null;
    }
}
