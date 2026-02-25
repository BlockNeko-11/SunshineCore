package io.github.blockneko11.sunshinecore.event.impl;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class EventInvokers {
    @ExpectPlatform
    public static void init() {
        throw new AssertionError();
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static void initClient() {
        throw new AssertionError();
    }
}
