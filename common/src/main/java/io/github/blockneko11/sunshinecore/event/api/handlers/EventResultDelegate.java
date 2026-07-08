package io.github.blockneko11.sunshinecore.event.api.handlers;

import io.github.blockneko11.sunshinecore.event.api.EventResult;
import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.Method;
import java.util.List;

@ApiStatus.Internal
public final class EventResultDelegate<T> extends EventDelegate<T> {
    public EventResultDelegate(List<T> handlers) {
        super(handlers);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        EventResult finalResult = EventResult.PASS;

        for (T handler : this.handlers) {
            EventResult result = (EventResult) method.invoke(handler, args);

            if (result != EventResult.PASS) {
                finalResult = result;
            }
        }

        return finalResult;
    }
}
