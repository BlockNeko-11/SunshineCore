package io.github.blockneko11.sunshinecore.event.forge;

import io.github.blockneko11.sunshinecore.event.ServerEvents;
import io.github.blockneko11.sunshinecore.event.level.ServerLevelEvents;
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

public final class ForgeEventHandlers {
    // Server Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        ServerEvents.BEFORE_START.invoker().handle(e.getServer());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStarting(ServerStartingEvent e) {
        ServerEvents.STARTING.invoker().handle(e.getServer());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStarted(ServerStartedEvent e) {
        ServerEvents.STARTED.invoker().handle(e.getServer());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStopping(ServerStoppingEvent e) {
        ServerEvents.STOPPING.invoker().handle(e.getServer());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerStopped(ServerStoppedEvent e) {
        ServerEvents.STOPPED.invoker().handle(e.getServer());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPre(ServerTickEvent.Pre e) {
        ServerEvents.PRE_TICK.invoker().handle(e.getServer());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPost(ServerTickEvent.Post e) {
        ServerEvents.POST_TICK.invoker().handle(e.getServer());
    }

    // Server Level Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvents.LOAD.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelUnload(LevelEvent.Unload e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvents.UNLOAD.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerLevelSave(LevelEvent.Save e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvents.SAVE.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvents.PRE_TICK.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onServerTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvents.POST_TICK.invoker().handle((ServerLevel) level);
    }

    // Player

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onPlayerTickPre(PlayerTickEvent.Pre e) {
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onPlayerTickPost(PlayerTickEvent.Post e) {
    }

    private ForgeEventHandlers() {
    }
}
