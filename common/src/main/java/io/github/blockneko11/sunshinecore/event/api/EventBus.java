package io.github.blockneko11.sunshinecore.event.api;

import io.github.blockneko11.sunshinecore.event.Event;
import net.jodah.typetools.TypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Event bus API, which is used for developers to subscribe cross-loader events (See {@link Event}).
 * Reference to <a href="https://github.com/neoforged/Bus">NeoForge's event bus</a>, under the LGPL-2.1 license.
 */
@Deprecated(since = "1.3.0")
public abstract class EventBus {
    private final Logger log;

    private final Map<Class<? extends Event>, Subscribers<?>> listeners = new ConcurrentHashMap<>();
    private ExceptionHandler handler;

    protected EventBus(String busName) {
        this.log = LoggerFactory.getLogger(busName);
        this.handler = (type, ex) -> {
            this.log.error("An exception has caught when posting event {}. Detail message: ", type.getCanonicalName(), ex);
        };
    }

    public void setHandler(ExceptionHandler handler) {
        this.handler = handler;
    }

    public void register(Class<?> clazz) {
        for (Method m : clazz.getDeclaredMethods()) {
            int mod = m.getModifiers();
            if (!Modifier.isStatic(mod)) {
                throw new IllegalArgumentException("Event listener should be static");
            }

            if (!m.isAnnotationPresent(Subscribe.class)) {
                continue;
            }

            this.registerMethod(m);
        }
    }

    private void registerMethod(Method m) {
        Class<?>[] paramTypes = m.getParameterTypes();

        if (paramTypes.length != 1) {
            throw new IllegalArgumentException("Event listener should have only one parameter");
        }

        Class<?> eventType = paramTypes[0];

        if (!Event.class.isAssignableFrom(eventType)) {
            throw new IllegalArgumentException("The first parameter of event listener should be implementation of event");
        }

        if (Modifier.isAbstract(eventType.getModifiers())) {
            throw new IllegalArgumentException("The first parameter of event listener should not be abstract events");
        }

        Subscribe annotation = m.getAnnotation(Subscribe.class);
        this.register0((Class<? extends Event>) eventType, new Subscriber.MethodSubscriber<>(m, annotation.priority()));
    }

    public <T extends Event> void register(Consumer<T> listener) {
        this.register(listener, 0);
    }

    public <T extends Event> void register(Consumer<T> listener, int priority) {
        this.register(this.getEventType(listener), listener, priority);
    }

    private <T extends Event> Class<T> getEventType(Consumer<T> listener) {
        Class<?> eventType = TypeResolver.resolveRawArgument(Consumer.class, listener.getClass());
        if (eventType == TypeResolver.Unknown.class) {
            throw new IllegalArgumentException("Cannot resolve event type");
        }

        return (Class<T>) eventType;
    }

    public <T extends Event> void register(Class<T> eventType, Consumer<T> listener) {
        this.register(eventType, listener, 0);
    }

    public <T extends Event> void register(Class<T> eventType, Consumer<T> listener, int priority) {
        this.register0(eventType, new Subscriber.ConsumerSubscriber<>(listener, priority));
    }

    private <T extends Event> void register0(Class<T> eventType, Subscriber<T> listener) {
        Subscribers<T> list = (Subscribers<T>) this.listeners.computeIfAbsent(
                eventType,
                $ -> new Subscribers<>());

        list.add(listener);
    }

    public <T extends Event> void post(T event) {
        Subscribers<T> list = (Subscribers<T>) this.listeners.get(event.getClass());
        if (list == null) {
            return;
        }

        list.post(event, this.handler);
    }
}
