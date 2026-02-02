package io.github.blockneko11.sunshinecore.universal;

import net.minecraft.resources.ResourceLocation;

public final class SIdentifier {
    public static ResourceLocation id(String modId, String path) {
        return ResourceLocation.fromNamespaceAndPath(modId, path);
    }

    private SIdentifier() {
    }
}
