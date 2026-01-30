package io.github.blockneko11.sunshinecore.util.forge;

import net.neoforged.bus.api.IEventBus;

import java.util.HashMap;
import java.util.Map;

public final class ModEventBuses {
    private static final Map<String, IEventBus> BUSES = new HashMap<>();

    public static void register(String modId, IEventBus bus) {
        BUSES.put(modId, bus);
    }

    public static IEventBus get(String modId) {
        return BUSES.get(modId);
    }
}
