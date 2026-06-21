package io.github.blockneko11.sunshinecore.client.event;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

/**
 * Game's lifecycle events on the client side.
 */
@Environment(EnvType.CLIENT)
public final class ClientEvents {
    public static final Event<Handler> STARTED = EventFactory.createNonReturn();
    public static final Event<Handler> STOPPING = EventFactory.createNonReturn();
    public static final Event<Handler> PRE_TICK = EventFactory.createNonReturn();
    public static final Event<Handler> POST_TICK = EventFactory.createNonReturn();

    @Environment(EnvType.CLIENT)
    @FunctionalInterface
    public interface Handler {
        void handle(Minecraft client);
    }

    private ClientEvents() {
    }
}
