package io.github.blockneko11.sunshinecore.client.fabric;

import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import io.github.blockneko11.sunshinecore.event.CommonEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class SunshineCoreClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SunshineCoreClient.initClient();
        CommonEvents.SETUP.invoker().onSetup();
    }
}
