package io.github.blockneko11.sunshinecore.util;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

/**
 * Cross-loader utilities.
 */
public final class Platform {
    @ExpectPlatform
    public static Loader getLoader() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Side getSide() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isDev() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Mod getMod(String modId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getGameDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }

    private Platform() {
    }
}
