package io.github.blockneko11.sunshinecore.event.impl.forge;

import io.github.blockneko11.sunshinecore.event.forge.ClientForgeEventHandlers;
import io.github.blockneko11.sunshinecore.event.forge.ForgeEventHandlers;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public final class EventInvokersImpl {
    public static void init() {
        EventBusUtils.FML().register(ForgeEventHandlers.class);
    }

    @OnlyIn(Dist.CLIENT)
    public static void initClient() {
        EventBusUtils.FML().register(ClientForgeEventHandlers.class);
    }
}
