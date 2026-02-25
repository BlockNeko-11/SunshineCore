package io.github.blockneko11.sunshinecore.event.fabric;

import io.github.blockneko11.sunshinecore.event.ServerEvents;
import io.github.blockneko11.sunshinecore.event.level.ServerLevelEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

public final class EventHandlers {
    public static void init() {

        // Server Lifecycle

        ServerLifecycleEvents.SERVER_STARTING.register(s -> {
            ServerEvents.BEFORE_START.invoker().handle(s);
        });

        ServerLifecycleEvents.SERVER_STARTED.register(s -> {
            ServerEvents.STARTED.invoker().handle(s);
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(s -> {
            ServerEvents.STOPPING.invoker().handle(s);
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(s -> {
            ServerEvents.STOPPED.invoker().handle(s);
        });

        ServerTickEvents.START_SERVER_TICK.register(s -> {
            ServerEvents.PRE_TICK.invoker().handle(s);
        });

        ServerTickEvents.END_SERVER_TICK.register(s -> {
            ServerEvents.POST_TICK.invoker().handle(s);
        });

        // Server Level Lifecycle

        ServerWorldEvents.LOAD.register((s, l) -> {
            ServerLevelEvents.LOAD.invoker().handle(l);
        });

        ServerWorldEvents.UNLOAD.register((s, l) -> {
            ServerLevelEvents.UNLOAD.invoker().handle(l);
        });

        ServerTickEvents.START_WORLD_TICK.register(l -> {
            ServerLevelEvents.PRE_TICK.invoker().handle(l);
        });

        ServerTickEvents.END_WORLD_TICK.register(l -> {
            ServerLevelEvents.POST_TICK.invoker().handle(l);
        });
    }
}
