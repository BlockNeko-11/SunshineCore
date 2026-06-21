package io.github.blockneko11.sunshinecore.server.fabric;

import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public final class SunshineCoreServerFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        SetupEvent.EVENT.invoker().onSetup();
    }
}
