package io.github.blockneko11.sunshinecore.eventbus;

import io.github.blockneko11.sunshinecore.event.Event;

import java.lang.reflect.Method;
import java.util.function.Consumer;

abstract class EventSubscriber<T extends Event> {
    private final int priority;

    EventSubscriber(int priority) {
        this.priority = priority;
    }

    protected abstract void handle0(T event) throws Exception;

    protected void handle(T event, ExceptionHandler handler) {
        try {
            this.handle0(event);
        } catch (Exception e) {
            handler.handle(event.getClass(), e);
        }
    }

    int getPriority() {
        return this.priority;
    }

    static int compare(EventSubscriber<?> a, EventSubscriber<?> b) {
        return b.priority - a.priority;
    }

    static final class MethodSubscriber<T extends Event> extends EventSubscriber<T> {
        private final Method method;

        MethodSubscriber(Method method, int priority) {
            super(priority);
            this.method = method;
        }

        @Override
        protected void handle0(T event) throws Exception {
            this.method.invoke(null, event);
        }
    }

    static final class ConsumerSubscriber<T extends Event> extends EventSubscriber<T> {
        private final Consumer<T> consumer;

        ConsumerSubscriber(Consumer<T> consumer, int priority) {
            super(priority);
            this.consumer = consumer;
        }

        @Override
        protected void handle0(T event) {
            this.consumer.accept(event);
        }
    }
}
