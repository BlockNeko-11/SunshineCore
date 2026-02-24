package io.github.blockneko11.sunshinecore.loader.forge;

import io.github.blockneko11.sunshinecore.loader.Loader;
import io.github.blockneko11.sunshinecore.loader.Mod;
import io.github.blockneko11.sunshinecore.loader.Side;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class PlatformImpl {
    public static Loader getLoader() {
        return Loader.NEOFORGE;
    }

    public static Side getSide() {
        return switch (FMLEnvironment.dist) {
            case CLIENT -> Side.CLIENT;
            case DEDICATED_SERVER -> Side.SERVER;
        };
    }

    public static boolean isDev() {
        return !FMLEnvironment.production;
    }

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static Mod getMod(String modId) {
        return new ModImpl(ModList.get().getModContainerById(modId).orElseThrow());
    }

    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }
}
