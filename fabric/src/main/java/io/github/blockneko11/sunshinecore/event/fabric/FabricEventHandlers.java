package io.github.blockneko11.sunshinecore.event.fabric;

import io.github.blockneko11.sunshinecore.event.tag.LoadTagsEvent;
import io.github.blockneko11.sunshinecore.server.event.ServerEvent;
import io.github.blockneko11.sunshinecore.server.event.level.ServerLevelEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

public final class FabricEventHandlers {
    public static void register() {
        // Common Lifecycle

        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            LoadTagsEvent.EVENT.invoker().onLoadTags(registries, client);
        });

        // Server Lifecycle

        ServerLifecycleEvents.SERVER_STARTING.register(s -> {
            ServerEvent.BEFORE_START.invoker().handle(s);
        });

        ServerLifecycleEvents.SERVER_STARTED.register(s -> {
            ServerEvent.STARTED.invoker().handle(s);
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(s -> {
            ServerEvent.STOPPING.invoker().handle(s);
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(s -> {
            ServerEvent.STOPPED.invoker().handle(s);
        });

        ServerTickEvents.START_SERVER_TICK.register(s -> {
            ServerEvent.PRE_TICK.invoker().handle(s);
        });

        ServerTickEvents.END_SERVER_TICK.register(s -> {
            ServerEvent.POST_TICK.invoker().handle(s);
        });

        // Server Level Lifecycle

        ServerWorldEvents.LOAD.register((s, l) -> {
            ServerLevelEvent.LOAD.invoker().handle(l);
        });

        ServerWorldEvents.UNLOAD.register((s, l) -> {
            ServerLevelEvent.UNLOAD.invoker().handle(l);
        });

        ServerTickEvents.START_WORLD_TICK.register(l -> {
            ServerLevelEvent.PRE_TICK.invoker().handle(l);
        });

        ServerTickEvents.END_WORLD_TICK.register(l -> {
            ServerLevelEvent.POST_TICK.invoker().handle(l);
        });
    }

    private FabricEventHandlers() {
    }
}
