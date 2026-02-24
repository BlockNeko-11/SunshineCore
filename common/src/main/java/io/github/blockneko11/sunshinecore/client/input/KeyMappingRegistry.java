package io.github.blockneko11.sunshinecore.client.input;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;

@Environment(EnvType.CLIENT)
public final class KeyMappingRegistry {
    @ExpectPlatform
    public static void register(KeyMapping mapping) {
        throw new AssertionError();
    }

    private KeyMappingRegistry() {
    }
}
