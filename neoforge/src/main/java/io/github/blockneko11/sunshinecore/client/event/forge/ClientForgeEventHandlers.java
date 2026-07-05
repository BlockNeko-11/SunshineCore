package io.github.blockneko11.sunshinecore.client.event.forge;

import io.github.blockneko11.sunshinecore.client.event.ClientEvent;
import io.github.blockneko11.sunshinecore.client.event.level.ClientLevelEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@OnlyIn(Dist.CLIENT)
public final class ClientForgeEventHandlers {
    // Client Lifecycle

    @SubscribeEvent
    public static void onClientTickPre(ClientTickEvent.Pre e) {
        ClientEvent.PRE_TICK.invoker().handle(Minecraft.getInstance());
    }

    @SubscribeEvent
    public static void onClientTickPost(ClientTickEvent.Post e) {
        ClientEvent.POST_TICK.invoker().handle(Minecraft.getInstance());
    }

    // Client Level Lifecycle

    @SubscribeEvent
    public static void onClientLevelLoad(LevelEvent.Load e) {
        LevelAccessor level = e.getLevel();
        if (level.isClientSide()) {
            ClientLevelEvent.LOAD.invoker().handle((ClientLevel) e.getLevel());
        }
    }

    @SubscribeEvent
    public static void onClientLevelTickPre(LevelTickEvent.Pre e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            ClientLevelEvent.PRE_TICK.invoker().handle((ClientLevel) e.getLevel());
        }
    }

    @SubscribeEvent
    public static void onClientLevelTickPost(LevelTickEvent.Post e) {
        Level level = e.getLevel();
        if (level.isClientSide()) {
            ClientLevelEvent.POST_TICK.invoker().handle((ClientLevel) e.getLevel());
        }
    }

    private ClientForgeEventHandlers() {
    }
}
