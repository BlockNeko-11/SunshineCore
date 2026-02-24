package io.github.blockneko11.sunshinecore.event.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.ServerEvent;
import io.github.blockneko11.sunshinecore.event.level.ServerLevelEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

public final class EventHandlers {
    public static void init() {

        // Server Lifecycle

        ServerLifecycleEvents.SERVER_STARTING.register(s -> {
            SunshineCore.BUS.post(new ServerEvent.BeforeStart(s));
        });

        ServerLifecycleEvents.SERVER_STARTED.register(s -> {
            SunshineCore.BUS.post(new ServerEvent.Started(s));
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(s -> {
            SunshineCore.BUS.post(new ServerEvent.Stopping(s));
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(s -> {
            SunshineCore.BUS.post(new ServerEvent.Stopped(s));
        });

        ServerTickEvents.START_SERVER_TICK.register(s -> {
            SunshineCore.BUS.post(new ServerEvent.PreTick(s));
        });

        ServerTickEvents.END_SERVER_TICK.register(s -> {
            SunshineCore.BUS.post(new ServerEvent.PostTick(s));
        });

        // Server Level Lifecycle

        ServerWorldEvents.LOAD.register((s, l) -> {
            SunshineCore.BUS.post(new ServerLevelEvent.Load(l));
        });

        ServerWorldEvents.UNLOAD.register((s, l) -> {
            SunshineCore.BUS.post(new ServerLevelEvent.Unload(l));
        });

        ServerTickEvents.START_WORLD_TICK.register(l -> {
            SunshineCore.BUS.post(new ServerLevelEvent.PreTick(l));
        });

        ServerTickEvents.END_WORLD_TICK.register(l -> {
            SunshineCore.BUS.post(new ServerLevelEvent.PostTick(l));
        });
    }
}
