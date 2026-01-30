package io.github.blockneko11.sunshinecore.client.fabric;

import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import net.fabricmc.api.ClientModInitializer;

public final class SunshineCoreClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SunshineCoreClient.initClient();
    }
}
