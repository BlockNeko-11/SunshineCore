package io.github.blockneko11.sunshinecore.util.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class EventBusUtils {
    public static IEventBus get(String modId) {
        return ModList.get().getModContainerById(modId).orElseThrow().getEventBus();
    }

    public static IEventBus SC() {
        return get(SunshineCore.MOD_ID);
    }

    public static IEventBus FML() {
        return NeoForge.EVENT_BUS;
    }

    private EventBusUtils() {
    }
}
