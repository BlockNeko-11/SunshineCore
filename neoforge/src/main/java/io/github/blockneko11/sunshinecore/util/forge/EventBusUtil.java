package io.github.blockneko11.sunshinecore.util.forge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;

public final class EventBusUtil {
    public static IEventBus get(String modId) {
        return ModList.get().getModContainerById(modId).orElseThrow().getEventBus();
    }
}
