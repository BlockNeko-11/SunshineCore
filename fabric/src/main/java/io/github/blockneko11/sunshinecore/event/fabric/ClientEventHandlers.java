package io.github.blockneko11.sunshinecore.event.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.event.level.ClientLevelLifecycleEvent;
import io.github.blockneko11.sunshinecore.client.event.ClientLifecycleEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class ClientEventHandlers {
    public static void initClient() {

        // Client Lifecycle

        ClientLifecycleEvents.CLIENT_STARTED.register(c -> {
            SunshineCore.SC_EVENT_BUS.post(new ClientLifecycleEvent.Started(c));
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(c -> {
            SunshineCore.SC_EVENT_BUS.post(new ClientLifecycleEvent.Stopping(c));
        });

        ClientTickEvents.START_CLIENT_TICK.register(c -> {
            SunshineCore.SC_EVENT_BUS.post(new ClientLifecycleEvent.PreTick(c));
        });

        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            SunshineCore.SC_EVENT_BUS.post(new ClientLifecycleEvent.PostTick(c));
        });

        // Client Level Lifecycle

        ClientTickEvents.START_WORLD_TICK.register(l -> {
            SunshineCore.SC_EVENT_BUS.post(new ClientLevelLifecycleEvent.PreTick(l));
        });

        ClientTickEvents.END_WORLD_TICK.register(l -> {
            SunshineCore.SC_EVENT_BUS.post(new ClientLevelLifecycleEvent.PostTick(l));
        });
    }
}
