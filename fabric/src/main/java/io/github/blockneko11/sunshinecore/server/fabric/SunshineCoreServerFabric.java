package io.github.blockneko11.sunshinecore.server.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.SetupEvent;
import net.fabricmc.api.DedicatedServerModInitializer;

public final class SunshineCoreServerFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        SunshineCore.SC_EVENT_BUS.post(new SetupEvent());
    }
}
