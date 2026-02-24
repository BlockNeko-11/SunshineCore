package io.github.blockneko11.sunshinecore.event.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.event.ClientEvent;
import io.github.blockneko11.sunshinecore.client.event.level.ClientLevelEvent;
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
        SunshineCore.BUS.post(new ClientEvent.PreTick(Minecraft.getInstance()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientTickPost(ClientTickEvent.Post e) {
        SunshineCore.BUS.post(new ClientEvent.PostTick(Minecraft.getInstance()));
    }

    // Client Level Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            SunshineCore.BUS.post(new ClientLevelEvent.Load((ClientLevel) level));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            SunshineCore.BUS.post(new ClientLevelEvent.PreTick((ClientLevel) level));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            SunshineCore.BUS.post(new ClientLevelEvent.PostTick((ClientLevel) level));
        }
    }
}
