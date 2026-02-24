package io.github.blockneko11.sunshinecore.event.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.ServerEvent;
import io.github.blockneko11.sunshinecore.event.level.ServerLevelEvent;
import io.github.blockneko11.sunshinecore.event.level.player.PlayerEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.*;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public final class EventHandlers {
    // Server Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        SunshineCore.BUS.post(new ServerEvent.BeforeStart(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStarting(ServerStartingEvent e) {
        SunshineCore.BUS.post(new ServerEvent.Starting(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStarted(ServerStartedEvent e) {
        SunshineCore.BUS.post(new ServerEvent.Started(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStopping(ServerStoppingEvent e) {
        SunshineCore.BUS.post(new ServerEvent.Stopping(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStopped(ServerStoppedEvent e) {
        SunshineCore.BUS.post(new ServerEvent.Stopped(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPre(ServerTickEvent.Pre e) {
        SunshineCore.BUS.post(new ServerEvent.PreTick(e.getServer()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPost(ServerTickEvent.Post e) {
        SunshineCore.BUS.post(new ServerEvent.PostTick(e.getServer()));
    }

    // Server Level Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.BUS.post(new ServerLevelEvent.Load((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelUnload(LevelEvent.Unload e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.BUS.post(new ServerLevelEvent.Unload((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelSave(LevelEvent.Save e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.BUS.post(new ServerLevelEvent.Save((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.BUS.post(new ServerLevelEvent.PreTick((ServerLevel) level));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }
        SunshineCore.BUS.post(new ServerLevelEvent.PostTick((ServerLevel) level));
    }

    // Player

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onPlayerTickPre(PlayerTickEvent.Pre e) {
        SunshineCore.BUS.post(new PlayerEvent.PreTick(e.getEntity()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onPlayerTickPost(PlayerTickEvent.Post e) {
        SunshineCore.BUS.post(new PlayerEvent.PostTick(e.getEntity()));
    }
}
