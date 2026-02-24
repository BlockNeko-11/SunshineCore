package io.github.blockneko11.sunshinecore.event.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.event.level.ClientLevelEvent;
import io.github.blockneko11.sunshinecore.client.event.ClientEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class ClientEventHandlers {
    public static void initClient() {

        // Client Lifecycle

        ClientLifecycleEvents.CLIENT_STARTED.register(c -> {
            SunshineCore.BUS.post(new ClientEvent.Started(c));
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(c -> {
            SunshineCore.BUS.post(new ClientEvent.Stopping(c));
        });

        ClientTickEvents.START_CLIENT_TICK.register(c -> {
            SunshineCore.BUS.post(new ClientEvent.PreTick(c));
        });

        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            SunshineCore.BUS.post(new ClientEvent.PostTick(c));
        });

        // Client Level Lifecycle

        ClientTickEvents.START_WORLD_TICK.register(l -> {
            SunshineCore.BUS.post(new ClientLevelEvent.PreTick(l));
        });

        ClientTickEvents.END_WORLD_TICK.register(l -> {
            SunshineCore.BUS.post(new ClientLevelEvent.PostTick(l));
        });
    }
}
