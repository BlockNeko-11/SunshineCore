package io.github.blockneko11.sunshinecore.client;

import io.github.blockneko11.sunshinecore.event.impl.EventInvokers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class SunshineCoreClient {
    public static void initClient() {
        EventInvokers.initClient();
    }

    private SunshineCoreClient() {
    }
}
