package io.github.blockneko11.sunshinecore.util;

import net.minecraft.resources.ResourceLocation;

public final class ResourceLoc {
    public static ResourceLocation id(String modId, String path) {
        return ResourceLocation.fromNamespaceAndPath(modId, path);
    }

    private ResourceLoc() {
    }
}
