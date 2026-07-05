package io.github.blockneko11.sunshinecore.client.event.fabric;

import io.github.blockneko11.sunshinecore.client.event.ClientEvent;
import io.github.blockneko11.sunshinecore.client.event.level.ClientLevelEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public final class ClientFabricEventHandlers {
    public static void registerClient() {
        // Client Lifecycle

        ClientLifecycleEvents.CLIENT_STARTED.register(c -> {
            ClientEvent.STARTED.invoker().handle(c);
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(c -> {
            ClientEvent.STOPPING.invoker().handle(c);
        });

        ClientTickEvents.START_CLIENT_TICK.register(c -> {
            ClientEvent.PRE_TICK.invoker().handle(c);
        });

        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            ClientEvent.POST_TICK.invoker().handle(c);
        });

        // Client Level Lifecycle

        ClientTickEvents.START_WORLD_TICK.register(l -> {
            ClientLevelEvent.PRE_TICK.invoker().handle(l);
        });

        ClientTickEvents.END_WORLD_TICK.register(l -> {
            ClientLevelEvent.POST_TICK.invoker().handle(l);
        });
    }

    private ClientFabricEventHandlers() {
    }
}
