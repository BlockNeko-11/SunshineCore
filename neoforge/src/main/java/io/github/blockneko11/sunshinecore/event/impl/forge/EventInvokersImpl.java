package io.github.blockneko11.sunshinecore.event.impl.forge;

import io.github.blockneko11.sunshinecore.event.forge.ClientForgeEventHandlers;
import io.github.blockneko11.sunshinecore.event.forge.ForgeEventHandlers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;

public final class EventInvokersImpl {
    public static void init() {
        NeoForge.EVENT_BUS.register(ForgeEventHandlers.class);
    }

    @OnlyIn(Dist.CLIENT)
    public static void initClient() {
        NeoForge.EVENT_BUS.register(ClientForgeEventHandlers.class);
    }
}
