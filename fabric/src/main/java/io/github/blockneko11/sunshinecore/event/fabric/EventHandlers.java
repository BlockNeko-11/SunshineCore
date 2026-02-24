package io.github.blockneko11.sunshinecore.event.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.ServerLifecycleEvent;
import io.github.blockneko11.sunshinecore.event.level.ServerLevelLifecycleEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

public final class EventHandlers {
    public static void init() {

        // Server Lifecycle

        ServerLifecycleEvents.SERVER_STARTING.register(s -> {
            SunshineCore.BUS.post(new ServerLifecycleEvent.BeforeStart(s));
        });

        ServerLifecycleEvents.SERVER_STARTED.register(s -> {
            SunshineCore.BUS.post(new ServerLifecycleEvent.Started(s));
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(s -> {
            SunshineCore.BUS.post(new ServerLifecycleEvent.Stopping(s));
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(s -> {
            SunshineCore.BUS.post(new ServerLifecycleEvent.Stopped(s));
        });

        ServerTickEvents.START_SERVER_TICK.register(s -> {
            SunshineCore.BUS.post(new ServerLifecycleEvent.PreTick(s));
        });

        ServerTickEvents.END_SERVER_TICK.register(s -> {
            SunshineCore.BUS.post(new ServerLifecycleEvent.PostTick(s));
        });

        // Server Level Lifecycle

        ServerWorldEvents.LOAD.register((s, l) -> {
            SunshineCore.BUS.post(new ServerLevelLifecycleEvent.Load(l));
        });

        ServerWorldEvents.UNLOAD.register((s, l) -> {
            SunshineCore.BUS.post(new ServerLevelLifecycleEvent.Unload(l));
        });

        ServerTickEvents.START_WORLD_TICK.register(l -> {
            SunshineCore.BUS.post(new ServerLevelLifecycleEvent.PreTick(l));
        });

        ServerTickEvents.END_WORLD_TICK.register(l -> {
            SunshineCore.BUS.post(new ServerLevelLifecycleEvent.PostTick(l));
        });
    }
}
