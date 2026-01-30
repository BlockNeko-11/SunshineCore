package io.github.blockneko11.sunshinecore.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import net.fabricmc.api.ModInitializer;

public final class SunshineCoreFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SunshineCore.init();
    }
}
