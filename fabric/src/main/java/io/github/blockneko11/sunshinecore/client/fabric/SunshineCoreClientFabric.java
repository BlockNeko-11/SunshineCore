package io.github.blockneko11.sunshinecore.client.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import io.github.blockneko11.sunshinecore.event.SetupEvent;
import io.github.blockneko11.sunshinecore.event.fabric.ClientEventHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class SunshineCoreClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SunshineCoreClient.initClient();
        ClientEventHandlers.initClient();
        SunshineCore.SC_EVENT_BUS.post(new SetupEvent());
    }
}
