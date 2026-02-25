package io.github.blockneko11.sunshinecore.universal;

import net.minecraft.resources.ResourceLocation;

/**
 * A cross-version identifier utility.
 */
public final class IdentifierUtil {
    /**
     * Create an identifier.
     * @param modId mod id
     * @param path path
     * @return an identifier
     */
    public static ResourceLocation id(String modId, String path) {
        return ResourceLocation.fromNamespaceAndPath(modId, path);
    }

    private IdentifierUtil() {
    }
}
