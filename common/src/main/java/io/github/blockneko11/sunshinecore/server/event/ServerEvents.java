package io.github.blockneko11.sunshinecore.server.event;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;
import net.minecraft.server.MinecraftServer;

/**
 * Game's lifecycle events on the server side.
 */
public final class ServerEvents {
    public static final Event<Handler> BEFORE_START = EventFactory.createNonReturn();
    public static final Event<Handler> STARTING = EventFactory.createNonReturn();
    public static final Event<Handler> STARTED = EventFactory.createNonReturn();
    public static final Event<Handler> STOPPING = EventFactory.createNonReturn();
    public static final Event<Handler> STOPPED = EventFactory.createNonReturn();
    public static final Event<Handler> PRE_TICK = EventFactory.createNonReturn();
    public static final Event<Handler> POST_TICK = EventFactory.createNonReturn();

    @FunctionalInterface
    public interface Handler {
        void handle(MinecraftServer server);
    }

    private ServerEvents() {
    }
}
