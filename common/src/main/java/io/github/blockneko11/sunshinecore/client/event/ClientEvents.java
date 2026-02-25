package io.github.blockneko11.sunshinecore.client.event;

import io.github.blockneko11.sunshinecore.event.api.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

/**
 * Game's lifecycle events on the client side.
 */
@Environment(EnvType.CLIENT)
public final class ClientEvents {
    public static final Event<Handler> STARTED = create();
    public static final Event<Handler> STOPPING = create();
    public static final Event<Handler> PRE_TICK = create();
    public static final Event<Handler> POST_TICK = create();

    @Environment(EnvType.CLIENT)
    @FunctionalInterface
    public interface Handler {
        void handle(Minecraft client);
    }

    private static Event<Handler> create() {
        return Event.create(invokers -> c -> {
            for (Handler handler : invokers) {
                handler.handle(c);
            }
        });
    }

    private ClientEvents() {
    }
}
