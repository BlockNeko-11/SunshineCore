package io.github.blockneko11.sunshinecore.event.forge;

import io.github.blockneko11.sunshinecore.event.tag.LoadTagsEvent;
import io.github.blockneko11.sunshinecore.server.event.ServerEvent;
import io.github.blockneko11.sunshinecore.server.event.level.ServerLevelEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.*;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public final class ForgeEventHandlers {
    // Common Lifecycle

    @SubscribeEvent
    public static void onTagsUpdated(TagsUpdatedEvent e) {
        LoadTagsEvent.EVENT.invoker().onLoadTags(e.getRegistryAccess(), !e.shouldUpdateStaticData());
    }

    // Server Lifecycle

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent e) {
        ServerEvent.BEFORE_START.invoker().handle(e.getServer());
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent e) {
        ServerEvent.STARTING.invoker().handle(e.getServer());
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent e) {
        ServerEvent.STARTED.invoker().handle(e.getServer());
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent e) {
        ServerEvent.STOPPING.invoker().handle(e.getServer());
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent e) {
        ServerEvent.STOPPED.invoker().handle(e.getServer());
    }

    @SubscribeEvent
    public static void onServerTickPre(ServerTickEvent.Pre e) {
        ServerEvent.PRE_TICK.invoker().handle(e.getServer());
    }

    @SubscribeEvent
    public static void onServerTickPost(ServerTickEvent.Post e) {
        ServerEvent.POST_TICK.invoker().handle(e.getServer());
    }

    // Server Level Lifecycle

    @SubscribeEvent
    public static void onServerLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvent.LOAD.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent
    public static void onServerLevelUnload(LevelEvent.Unload e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvent.UNLOAD.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent
    public static void onServerLevelSave(LevelEvent.Save e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvent.SAVE.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent
    public static void onServerTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvent.PRE_TICK.invoker().handle((ServerLevel) level);
    }

    @SubscribeEvent
    public static void onServerTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            return;
        }

        ServerLevelEvent.POST_TICK.invoker().handle((ServerLevel) level);
    }

    // Player

    @SubscribeEvent
    public static void onPlayerTickPre(PlayerTickEvent.Pre e) {
    }

    @SubscribeEvent
    public static void onPlayerTickPost(PlayerTickEvent.Post e) {
    }

    private ForgeEventHandlers() {
    }
}
