package io.github.blockneko11.sunshinecore.server.fabric;

import io.github.blockneko11.sunshinecore.event.CommonEvents;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public final class SunshineCoreServerFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        CommonEvents.SETUP.invoker().onSetup();
    }
}
