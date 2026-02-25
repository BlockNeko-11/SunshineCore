package io.github.blockneko11.sunshinecore.event.forge;

import io.github.blockneko11.sunshinecore.client.event.ClientEvents;
import io.github.blockneko11.sunshinecore.client.event.level.ClientLevelEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@OnlyIn(Dist.CLIENT)
public final class ClientForgeEventHandlers {
    // Client Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientTickPre(ClientTickEvent.Pre e) {
        ClientEvents.PRE_TICK.invoker().handle(Minecraft.getInstance());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientTickPost(ClientTickEvent.Post e) {
        ClientEvents.POST_TICK.invoker().handle(Minecraft.getInstance());
    }

    // Client Level Lifecycle

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (!level.isClientSide()) {
            ClientLevelEvents.LOAD.invoker().handle((ClientLevel) e.getLevel());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (!level.isClientSide()) {
            ClientLevelEvents.PRE_TICK.invoker().handle((ClientLevel) e.getLevel());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onClientLevelTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            ClientLevelEvents.POST_TICK.invoker().handle((ClientLevel) e.getLevel());
        }
    }
}
