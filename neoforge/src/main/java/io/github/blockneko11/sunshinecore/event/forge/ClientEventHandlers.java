package io.github.blockneko11.sunshinecore.event.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.client.level.ClientLevelLifecycleEvent;
import io.github.blockneko11.sunshinecore.event.client.lifecycle.ClientLifecycleEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public final class ClientEventHandlers {

    // Client Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientTickPre(ClientTickEvent.Pre e) {
        SunshineCore.SC_EVENT_BUS.post(new ClientLifecycleEvent.PreTick(Minecraft.getInstance()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientTickPost(ClientTickEvent.Post e) {
        SunshineCore.SC_EVENT_BUS.post(new ClientLifecycleEvent.PostTick(Minecraft.getInstance()));
    }

    // Client Level Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            SunshineCore.SC_EVENT_BUS.post(new ClientLevelLifecycleEvent.Load((ClientLevel) level));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            SunshineCore.SC_EVENT_BUS.post(new ClientLevelLifecycleEvent.PreTick((ClientLevel) level));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            SunshineCore.SC_EVENT_BUS.post(new ClientLevelLifecycleEvent.PostTick((ClientLevel) level));
        }
    }
}
