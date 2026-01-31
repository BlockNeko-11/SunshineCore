package io.github.blockneko11.sunshinecore.event.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.ServerLifecycleEvent;
import io.github.blockneko11.sunshinecore.event.level.ServerLevelLifecycleEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.*;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public final class EventHandlers {

    // Server Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        SunshineCore.SC_EVENT_BUS.post(new ServerLifecycleEvent.BeforeStart(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStarting(ServerStartingEvent e) {
        SunshineCore.SC_EVENT_BUS.post(new ServerLifecycleEvent.Starting(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStarted(ServerStartedEvent e) {
        SunshineCore.SC_EVENT_BUS.post(new ServerLifecycleEvent.Started(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStopping(ServerStoppingEvent e) {
        SunshineCore.SC_EVENT_BUS.post(new ServerLifecycleEvent.Stopping(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStopped(ServerStoppedEvent e) {
        SunshineCore.SC_EVENT_BUS.post(new ServerLifecycleEvent.Stopped(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPre(ServerTickEvent.Pre e) {
        SunshineCore.SC_EVENT_BUS.post(new ServerLifecycleEvent.PreTick(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPost(ServerTickEvent.Post e) {
        SunshineCore.SC_EVENT_BUS.post(new ServerLifecycleEvent.PostTick(e.getServer()));
    }

    // Server Level Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.SC_EVENT_BUS.post(new ServerLevelLifecycleEvent.Load((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelUnload(LevelEvent.Unload e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.SC_EVENT_BUS.post(new ServerLevelLifecycleEvent.Unload((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelSave(LevelEvent.Save e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.SC_EVENT_BUS.post(new ServerLevelLifecycleEvent.Save((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.SC_EVENT_BUS.post(new ServerLevelLifecycleEvent.PreTick((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.SC_EVENT_BUS.post(new ServerLevelLifecycleEvent.PostTick((ServerLevel) level));
    }
}
