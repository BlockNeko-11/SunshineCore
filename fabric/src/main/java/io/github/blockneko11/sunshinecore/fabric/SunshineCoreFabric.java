package io.github.blockneko11.sunshinecore.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.fabric.FabricEventHandlers;
import net.fabricmc.api.ModInitializer;

public final class SunshineCoreFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SunshineCore.init();
        FabricEventHandlers.register();
    }
}
