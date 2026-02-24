package io.github.blockneko11.sunshinecore.loader.fabric;

import io.github.blockneko11.sunshinecore.loader.Loader;
import io.github.blockneko11.sunshinecore.loader.Mod;
import io.github.blockneko11.sunshinecore.loader.Side;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public final class PlatformImpl {
    public static Loader getLoader() {
        return Loader.FABRIC;
    }

    public static Side getSide() {
        return switch (FabricLoader.getInstance().getEnvironmentType()) {
            case CLIENT -> Side.CLIENT;
            case SERVER -> Side.SERVER;
        };
    }

    public static boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    public static Mod getMod(String modId) {
        return new ModImpl(FabricLoader.getInstance().getModContainer(modId).orElseThrow());
    }

    public static Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
