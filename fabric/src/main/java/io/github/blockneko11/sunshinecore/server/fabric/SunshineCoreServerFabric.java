package io.github.blockneko11.sunshinecore.server.fabric;

import io.github.blockneko11.sunshinecore.event.CommonEvents;
import net.fabricmc.api.DedicatedServerModInitializer;

public final class SunshineCoreServerFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        CommonEvents.SETUP.invoker().onSetup();
    }
}
