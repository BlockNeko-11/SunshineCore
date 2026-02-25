package io.github.blockneko11.sunshinecore.event;

import io.github.blockneko11.sunshinecore.event.api.Event;
import net.minecraft.server.MinecraftServer;

/**
 * Game's lifecycle events on the server side.
 */
public final class ServerEvents {
    public static final Event<Handler> BEFORE_START = create();
    public static final Event<Handler> STARTING = create();
    public static final Event<Handler> STARTED = create();
    public static final Event<Handler> STOPPING = create();
    public static final Event<Handler> STOPPED = create();
    public static final Event<Handler> PRE_TICK = create();
    public static final Event<Handler> POST_TICK = create();

    @FunctionalInterface
    public interface Handler {
        void handle(MinecraftServer server);
    }

    private static Event<Handler> create() {
        return Event.create(handlers -> s -> {
            for (Handler handler : handlers) {
                handler.handle(s);
            }
        });
    }

    private ServerEvents() {
    }
}
